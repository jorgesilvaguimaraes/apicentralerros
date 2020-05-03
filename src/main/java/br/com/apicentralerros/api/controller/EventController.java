package br.com.apicentralerros.api.controller;

import br.com.apicentralerros.api.model.input.EventLogInputModel;
import br.com.apicentralerros.api.model.output.EventLogOutPutModel;
import br.com.apicentralerros.api.model.output.EventOutPutModel;
import br.com.apicentralerros.domain.entity.Event;
import br.com.apicentralerros.domain.service.impl.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "API REST Central de Erros")
@RestController
@RequestMapping("/event")
public class EventController {


    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Retorna uma lista de eventos")
    @GetMapping
    public List<EventOutPutModel> index(@RequestParam Optional<String>  level, @RequestParam Optional<String> description, @RequestParam Optional<String> origin, @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Optional<LocalDate> date, Pageable pageable){
        return toCollection(this.eventService.index( level, description, origin, date, pageable));
    }

    @ApiOperation(value = "Salva um evento")
    @PostMapping
    public ResponseEntity<EventOutPutModel> store(@Valid @RequestBody EventLogInputModel eventLogInputModel){
         Event event = toEntity(eventLogInputModel);
        return new ResponseEntity<EventOutPutModel>(toModel(this.eventService.store(event)), HttpStatus.CREATED) ;
    }

    @ApiOperation(value = "Retorna uma evento especifico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EventLogOutPutModel> show(@PathVariable("id") Integer id) {

        Optional<Event> event = this.eventService.show(id);
        EventLogOutPutModel eventLogModel = this.modelMapper.map(event.get(), EventLogOutPutModel.class);

        if(event.isPresent()){
            return ResponseEntity.ok(eventLogModel);
        }

        return ResponseEntity.notFound().build();
    }

    private EventOutPutModel toModel(Event event){
        return this.modelMapper.map(event, EventOutPutModel.class);
    }

    private List<EventOutPutModel> toCollection(List<Event> eventList){
        return eventList.stream().map(event -> toModel(event)).collect(Collectors.toList());
    }

    private Event toEntity(EventLogInputModel eventLogInputModel) {

        this.modelMapper.addMappings(new PropertyMap<Event, EventLogInputModel>() {
            @Override
            protected void configure() {
            }
        });
        this.modelMapper.addMappings(new PropertyMap<EventLogInputModel, Event>() {
            @Override
            protected void configure() {
            }
        });

        // need to add the parent link to jpa order items (as these don't exist in dto form)
        this.modelMapper.getTypeMap(EventLogInputModel.class, Event.class)
                .setPostConverter(context -> {
                    Event target = context.getDestination();
                    if (target.getLogs() != null) {
                        target.getLogs().stream().forEach((log) -> log.setEvent(target));
                    }
                    return target;
                });


         Event event = this.modelMapper.map(eventLogInputModel, Event.class);

        return event;
    }


}
