package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

import Model.FixedSalary;
import Model.Internship;
import Model.InternshipList;

public class InternshipListTester {
    ArrayList<Internship> internships = new ArrayList<>(Arrays.asList(
            new Internship("Test intern", "Frau Mustermann", "Test", new ArrayList<>(Arrays.asList("python", "java")),
                    LocalDate.now(), LocalDate.now().plusDays(1), 1, 15, LocalDate.now().plusDays(3),
                    new FixedSalary(44)),
            new Internship("Intern Test", "Pete", "Test", new ArrayList<>(Arrays.asList("python", "java")),
                    LocalDate.now(), LocalDate.now().plusDays(1), 1, 15, LocalDate.now().plusDays(3),
                    new FixedSalary(44))));

    @Test
    public void testGetInstance() {
        InternshipList.getInstance().setInternshipList(internships);
        assertEquals(internships, InternshipList.getInstance().getInternships());
    }

    @Test
    public void testGetInternshipByIdIfExistsWithId() {
        InternshipList.getInstance().setInternshipList(internships);
        Internship internship = internships.get(0);
        assertEquals(internship, InternshipList.getInstance().getInternshipById(internship.getId()));
    }

    @Test
    public void testGetInternshipByIdIfNotExistsWithId() {
        InternshipList.getInstance().setInternshipList(internships);
        assertEquals(null, InternshipList.getInstance().getInternshipById(UUID.randomUUID()));
    }

    @Test
    public void testGetInternshipByIdIfListEmpty() {
        InternshipList.getInstance().setInternshipList(new ArrayList<>());
        assertEquals(null, InternshipList.getInstance().getInternshipById(UUID.randomUUID()));
    }

    @Test
    public void testGetInternshipByIdIfIdNull() {
        InternshipList.getInstance().setInternshipList(internships);
        assertEquals(null, InternshipList.getInstance().getInternshipById(null));
    }

    @Test
    public void testRemoveByIdIfIdIsInList() {
        ArrayList<Internship> internshipsTwo = (ArrayList<Internship>) internships.clone();
        Internship notToContain = internshipsTwo.get(0);
        InternshipList.getInstance().setInternshipList(internshipsTwo);
        InternshipList.getInstance().removeInternshipById(notToContain.getId());
        assertTrue(!InternshipList.getInstance().getInternships().contains(notToContain));
    }

    @Test
    public void testRemoveByIdIfIdIsNotInList() {
        InternshipList.getInstance().setInternshipList(internships);
        InternshipList.getInstance().removeInternshipById(UUID.randomUUID());
        assertEquals(internships, InternshipList.getInstance().getInternships());
    }

    @Test
    public void testRemoveByIdIfIdIsNull() {
        InternshipList.getInstance().setInternshipList(internships);
        InternshipList.getInstance().removeInternshipById(null);
        assertEquals(internships, InternshipList.getInstance().getInternships());
    }

    @Test
    public void addInternship() {
        InternshipList.getInstance().setInternshipList(new ArrayList<>());
        Internship toAdd = new Internship("Test intern", "Frau Mustermann", "Test",
                new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1), 1, 15,
                LocalDate.now().plusDays(3), new FixedSalary(44));

        InternshipList.getInstance().addInternship(toAdd);
        assertTrue(InternshipList.getInstance().getInternships().contains(toAdd));
    }

    @Test
    public void testAddInternshipIfInternshipIsNull(){
        InternshipList.getInstance().setInternshipList(internships);
        InternshipList.getInstance().addInternship(null);
        assertEquals(internships.get(internships.size()-1), InternshipList.getInstance().getInternships().get(InternshipList.getInstance().getInternships().size()-1));
    }

    @Test
    public void testGetInternshipByKeywordIfKeywordExists(){
        InternshipList.getInstance().setInternshipList(internships);
        ArrayList<Internship> expected = new ArrayList<>(Arrays.asList(internships.get(0)));
        assertEquals(expected, InternshipList.getInstance().getInternshipByKeyword("Mustermann"));
    }

    @Test
    public void testGetInternshipByKeywordIfKeywordExistsInSeveral(){
        InternshipList.getInstance().setInternshipList(internships);
        assertEquals(internships, InternshipList.getInstance().getInternshipByKeyword("python"));
    }

    @Test
    public void testGetInternshipByKeywordIfKeywordNotExists(){
        InternshipList.getInstance().setInternshipList(internships);
        assertEquals(new ArrayList<>(), InternshipList.getInstance().getInternshipByKeyword("Nope"));
    }

    @Test
    public void testGetInternshipByKeywordIfKeywordIsNull(){
        InternshipList.getInstance().setInternshipList(internships);
        assertEquals(new ArrayList<>(), InternshipList.getInstance().getInternshipByKeyword(null));
    }
}
