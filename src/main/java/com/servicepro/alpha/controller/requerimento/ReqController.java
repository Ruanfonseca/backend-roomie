package com.servicepro.alpha.controller.requerimento;

import com.servicepro.alpha.domain.Requerimento;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitations")
public class ReqController {

    @GetMapping
    public List<Requerimento> getAll() { return null; }

    @PostMapping
    public Requerimento create(@RequestBody Requerimento req) { return null;}

    @PutMapping("/{id}")
    public Requerimento update(@PathVariable String id, @RequestBody Requerimento req) {return null; }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) { }
}

