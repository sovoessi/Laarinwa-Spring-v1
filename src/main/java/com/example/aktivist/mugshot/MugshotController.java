package com.example.aktivist.mugshot;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/laarinwa")
@AllArgsConstructor
public class MugshotController {

    private final MugshotService mugshotService;

    @GetMapping
    public List<Mugshot> fetchAllMugshots(){
        return mugshotService.getAllMugshots();
    }


    @PostMapping
    public ResponseEntity<Mugshot> createMugshot(@Valid @RequestBody Mugshot mugshot){
        Mugshot created = mugshotService.saveMugshot(mugshot);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{mugshotId}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{mugshotId}")
    public ResponseEntity<Mugshot> fetchMugshotById(@PathVariable("mugshotId") String id){
        Optional<Mugshot> found = mugshotService.getMugshotById(id);
        return ResponseEntity.of(found);
    }

    @PutMapping("/{mugshotId}")
    public ResponseEntity<Mugshot> updateMugshot(
            @Valid @PathVariable("mugshotId") String id,
            @RequestBody Mugshot updatedMugshot){
        Optional<Mugshot> updated = mugshotService.updateMugshot(id, updatedMugshot);
        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Mugshot created = mugshotService.saveMugshot(updatedMugshot);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{mugshotId}")
                            .buildAndExpand(created.getId())
                            .toUri();

                    return ResponseEntity.created(location).body(created);
                });
    }

    @DeleteMapping("/{mugshotId}")
    public ResponseEntity<Mugshot> delete(@PathVariable("mugshotId") String id) {
        mugshotService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach((error) -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });
        return ResponseEntity.badRequest().body(map);
    }
}
