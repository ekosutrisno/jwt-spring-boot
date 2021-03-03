package app.app02.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.app02.model.Agama;

/**
 * HomeController
 */
@RestController
@RequestMapping("/")
public class HomeController {

    private static final List<Agama> AGAMA = Arrays.asList(
            new Agama(1L, "A001", "ISLAM"),
            new Agama(2L, "A002", "KRISTEN"),
            new Agama(3L, "A003", "HINDU"),
            new Agama(4L, "A004", "BUDHA"));

    @GetMapping("all")
    public List<Agama> getAll() {
        return new ArrayList<>(AGAMA);
    }

    @GetMapping(path = "all/{agamaId}")
    public ResponseEntity<?> getById(@PathVariable("agamaId") Long agamaId) {
        Optional<Agama> agama = AGAMA.stream().filter(agm -> agamaId.equals(agm.getId())).findFirst();

        if (agama.isPresent())
            return new ResponseEntity<>(agama.get(), HttpStatus.OK);

        return new ResponseEntity<>("Data dengan id (" + agamaId + ") tidak ditemukan.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public String insertData() {
        return "Add Data";
    }

    @PutMapping("update")
    public String updateData() {
        return "Update Data";
    }

    @DeleteMapping("delete")
    public String deleteData() {
        return "Delete Data";
    }

}