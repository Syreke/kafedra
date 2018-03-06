package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Prepod;
import kz.kaznitu.lessons.models.Student;
import kz.kaznitu.lessons.reposotories.PrepodRepository;
import kz.kaznitu.lessons.reposotories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

import java.util.List;

@Controller
@RequestMapping(path = "/student")

public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_add_form";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/student/all2";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<Student> allStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/all2")
    public String allStudents2(Model model) {
        List<Student> students = (List<Student>) studentRepository.findAll();
        model.addAttribute("students", students);
        return "student";
    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    public ModelAndView deletePrepod(@RequestParam("id") long idd) {
        studentRepository.deleteById(idd);
        return new ModelAndView("redirect:/student/all2");
    }
    @PostMapping("/editStudent")
    public String editStudent(@ModelAttribute Student student) {
        Student stud1 = new Student();
        stud1.setId(a);
        stud1.setFirstName(student.getFirstName());
        stud1.setLastName(student.getLastName());
        stud1.setEmail(student.getEmail());
        stud1.setKafedra(student.getKafedra());
        studentRepository.save(stud1);
        return "redirect:/student/all2";
    }

    @RequestMapping(value = "/editStudent",method = RequestMethod.GET)
    public ModelAndView editPrepod(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Student> student = (Optional <Student>) studentRepository.findById(id);
        model.addAttribute("student",student);
        return new ModelAndView("smpp");
    }
    @RequestMapping("/editStudent")
    public String showForm2(Model model){
        model.addAttribute("student",new Student());
        return "smpp";
    }
}