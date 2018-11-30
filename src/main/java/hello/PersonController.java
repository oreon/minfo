package hello;

import ca.bluecross.ab.idbl.model.DrugDetail;
import ca.bluecross.ab.idbl.model.ReviewDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
        detail.setDateListed(new Date());
        detail.setPharmaTheraClassificationId("456666");
        detail.setRouteCode("Injectable");
        detail.setCoverageStatus("UNDER REVIEW");
        detail.setProvScheduleCode("U3");
        detail.setInterchangeable("Yes");
        detail.setAigCode("M01AE01");
        //TODO detail.getunits()
        detail.setClientsAppliedTo("Child and Family Services (Group 20403)\n" +
                "Alberta Child Health Benefit (Group 20400, 20401, 20402)\n" +
                "Children and Youth Services (Group 19824)\n" +
                "Income Support (Group 19823)\n" +
                "Learners Program (Group 22128)\n" +
                "Alberta Human Services (AISH) (Group 19823)\n" +
                "Alberta Adult Health Benefit (AAHB) (Group 23609)");
       // detail.setAigCode("123");
        detail.setDinPin("123456789");
        detail.setTrodsReviews(createReviews());
        detail.setQuotePrice(new BigDecimal(23.25));
        detail.setManufacturer( SampleData.ALC );
        return detail;
    }

    List<ReviewDetail> createReviews(){
        List<ReviewDetail> reviews = new ArrayList<>();
        ReviewDetail review = new ReviewDetail("First Review", "CDR");
        ReviewDetail review2 = new ReviewDetail("Second Review", "CDR");
        reviews.add(review);
        reviews.add(review2);
        return reviews;
    }
}