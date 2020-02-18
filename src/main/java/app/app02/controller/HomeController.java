package app.app02.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.app02.model.Agama;

/**
 * HomeController
 */
@RestController
@RequestMapping("/")
public class HomeController {

  private static final List<Agama> AGAMA = Arrays.asList(new Agama(1l, "A001", "ISLAM"),
      new Agama(2l, "A002", "KRISTEN"), new Agama(3l, "A003", "HINDU"), new Agama(4l, "A004", "BUDHA"));

  @GetMapping("all")
  public List<Agama> getAll() {
    List<Agama> agama = new ArrayList<>();
    agama.addAll(AGAMA);
    return agama;
  }

  @GetMapping(path = "all/{agamaId}")
  public ResponseEntity<?> getAgamaById(@PathVariable("agamaId") Long agamaId) {
    Optional<Agama> agama = AGAMA.stream().filter(agm -> agamaId.equals(agm.getId())).findFirst();

    if (agama.isPresent())
      return new ResponseEntity<>(agama.get(), HttpStatus.OK);

    return new ResponseEntity<>("Agama dengan id (" + agamaId + ") tidak ditemukan.", HttpStatus.NOT_FOUND);
  }

  @GetMapping("add")
  public String insertData() {
    return "Tambah Data";
  }

  @GetMapping("update")
  public String updateData() {
    return "Update Data";
  }

  @GetMapping("delete")
  public String deleteData() {
    return "Delete Data";
  }

}