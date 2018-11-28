package hello;

import ca.bluecross.ab.idbl.model.DrugDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {

        this.personService = personService;
        drugs.add(createDrug("Humira", "111", "adalimumab"));
        drugs.add(createDrug("Advil", "112", "Ibuprofen"));
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("person", new Person());
        return "person";
    }

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public String addPagePerson(@ModelAttribute Person person, Model model) {
        personService.createPerson(person);
        model.addAttribute("persons", personService.getAllPersons());
        return "people";
    }

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String getAllPersons( Model model) {
        //personService.createPerson(person);
        model.addAttribute("persons", personService.getAllPersons());
        return "people";
    }

    List<DrugDetail> drugs = new ArrayList<>();

    @RequestMapping(value = "/drugs", method = RequestMethod.GET)
    public String getDrugs( Model model) {



        //personService.createPerson(person);
        model.addAttribute("drugs", drugs);
        return "drugList";
    }


    @RequestMapping(value = "/drugDetails", method = RequestMethod.GET)
    public String getDrugDetails(@RequestParam(name="id", required=true, defaultValue="111") String id,
                                 Model model) {

        DrugDetail drug = drugs.stream().filter(x -> x.getProductId() .equalsIgnoreCase( id)).findFirst().get();

        if(drug == null){
            throw new RuntimeException("Cannot find drug with id " + id);
        }
        model.addAttribute("drug", drug);
        return "drugDetails";
    }

    private DrugDetail createDrug(String name, String id, String genericName) {
        DrugDetail detail = new DrugDetail(id);
        detail.setBrandName(name);
        detail.setGenericName(genericName);
        detail.setQuotePrice(new BigDecimal(23.25));
        detail.setManufacturer( SampleData.ALC );
        return detail;
    }
}