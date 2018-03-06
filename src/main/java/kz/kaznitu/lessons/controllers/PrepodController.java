package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Prepod;
import kz.kaznitu.lessons.reposotories.PrepodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

import java.util.List;

@Controller
@RequestMapping(path = "/demo")

public class PrepodController {

    @Autowired
    private PrepodRepository prepodRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("prepod", new Prepod());
        return "prepod_add_form";
    }

    @PostMapping("/add")
    public String addPrepod(@ModelAttribute Prepod prepod) {
        prepodRepository.save(prepod);
        return "redirect:/demo/all2";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Prepod> allPrepods() {
        return prepodRepository.findAll();
    }

    @GetMapping("/all2")
    public String allPrepods2(Model model) {
        List<Prepod> prepods = (List<Prepod>) prepodRepository.findAll();
        model.addAttribute("prepods", prepods);
        return "prepods";
    }

    @RequestMapping(value = "/deletePrepod", method = RequestMethod.GET)
    public ModelAndView deletePrepod(@RequestParam("id") long idd) {
        prepodRepository.deleteById(idd);
        return new ModelAndView("redirect:/demo/all2");
    }
    @PostMapping("/editPrepod")
    public String editPrepod(@ModelAttribute Prepod prepod) {
        Prepod prepod1 = new Prepod();
        prepod1.setId(a);
        prepod1.setFirstName(prepod.getFirstName());
        prepod1.setLastName(prepod.getLastName());
        prepod1.setEmail(prepod.getEmail());
        prepodRepository.save(prepod1);
        return "redirect:/demo/all2";
    }

    @RequestMapping(value = "/editPrepod",method = RequestMethod.GET)
    public ModelAndView editPrepod(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Prepod> prepod = (Optional <Prepod>) prepodRepository.findById(id);
        model.addAttribute("prepod",prepod);
        return new ModelAndView("smp");
    }
    @RequestMapping("/editPrepod")
    public String showForm2(Model model){
        model.addAttribute("prepod",new Prepod());
        return "smp";
    }
}