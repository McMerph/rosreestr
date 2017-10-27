package ru.mcmerphy.rosreestr.model.domain.parser;

import org.junit.Test;
import ru.mcmerphy.rosreestr.model.FlatParserFailException;
import ru.mcmerphy.rosreestr.model.domain.*;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * {@link FlatParser} test class
 */
public class FlatParserTest {

    private static final String PERSONS_OWNERS = "/persons-owners.xml";
    private static final String GOVERNANCE_OWNER = "/governance-owner.xml";
    private static final String ORGANIZATION_OWNER = "/organization-owner.xml";
    private static final String NOT_FLAT = "/not-flat.xml";
    private static final String NOT_FLAT_WITH_FLAT_TAG = "/not-flat-with-flat-tag.xml";
    private static final String RIGHT_NOT_FOUND = "/right-not-found.xml";
    private static final String PERSONS_OWNER_NOT_GOVERNANCE = "/person-owner-not-governance.xml";
    private static final String VILLAGE = "/village.xml";

    /**
     * Test parsing xml file with village.
     */
    @Test
    public void testParseFileWithVillage() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(VILLAGE);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        Flat parsedFlat = flatParser.getFlat();
        assertTrue(parsedFlat.isConsistent());

        Flat flat = new Flat();
        flat.setTotalArea(new BigDecimal("47.6"));

        Right right = new Right();
        right.setOwner("Кобяков Вячеслав Юрьевич");
        right.setRegistration("Собственность, № 27-27-01/066/2006-414 от 20.09.2006");
        flat.getRights().add(right);

        Address address = new Address();
        address.setRegion("27");
        address.setDistrict(new District("р-н", "Хабаровский"));
        address.setLocality(new Locality("с", "Калинка"));
        address.setStreet(new Street("ул", "Молодежная"));
        address.setBuilding(new Building("д", "1"));
        address.setApartment(new Apartment("кв", "52"));

        flat.setAddress(address);

        assertThat(parsedFlat, is(flat));
    }

    /**
     * Test parsing xml file with person owner, not governance.
     */
    @Test
    public void testParseFileWithPersonOwnerNotGovernance() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(PERSONS_OWNER_NOT_GOVERNANCE);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        Flat parsedFlat = flatParser.getFlat();
        assertTrue(parsedFlat.isConsistent());

        Flat flat = new Flat();
        flat.setTotalArea(new BigDecimal("61"));

        Right right = new Right();
        right.setOwner("Рыбаков Сергей Валерьевич");
        right.setRegistration("Собственность, № 27-27-01/007/2014-943 от 26.02.2014");
        flat.getRights().add(right);

        Address address = new Address();
        address.setRegion("27");
        address.setLocality(new Locality("г", "Хабаровск"));
        address.setStreet(new Street("пер", "Краснореченский"));
        address.setBuilding(new Building("д", "4"));
        address.setApartment(new Apartment("кв", "25"));

        flat.setAddress(address);

        assertThat(parsedFlat, is(flat));
    }

    /**
     * Test parsing consistent xml file with person's owners.
     */
    @Test
    public void testParseConsistentFileWithPersonsOwners() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(PERSONS_OWNERS);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        Flat parsedFlat = flatParser.getFlat();
        assertTrue(parsedFlat.isConsistent());

        Flat flat = new Flat();
        flat.setTotalArea(new BigDecimal("43"));

        Right right1 = new Right();
        right1.setOwner("Глушицкая Ольга Викторовна");
        right1.setRegistration("Долевая собственность, № 27-01/11-57/2004-879 от 06.10.2004, 1/2");
        Right right2 = new Right();
        right2.setOwner("Аистова Зинаида Николаевна");
        right2.setRegistration("Долевая собственность, № 27-01/11-57/2004-879 от 06.10.2004, 1/2");
        flat.getRights().addAll(Arrays.asList(right1, right2));

        Address address = new Address();
        address.setRegion("27");
        address.setLocality(new Locality("г", "Хабаровск"));
        address.setStreet(new Street("пер", "Краснореченский"));
        address.setBuilding(new Building("д", "8"));
        address.setApartment(new Apartment("кв", "44"));

        flat.setAddress(address);

        assertThat(parsedFlat, is(flat));
    }

    /**
     * Test parsing consistent xml file with governance owner
     */
    @Test
    public void testParseConsistentFileWithGovernanceOwner() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(GOVERNANCE_OWNER);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        Flat parsedFlat = flatParser.getFlat();
        assertTrue(parsedFlat.isConsistent());

        Flat flat = new Flat();
        flat.setTotalArea(new BigDecimal("83.1"));

        Right right1 = new Right();
        right1.setOwner("Городской округ \"Город Хабаровск\"");
        right1.setRegistration("Долевая собственность, № 27-27-01/004/2009-346 от 11.03.2009, 64/100");
        Right right2 = new Right();
        right2.setOwner("Мартинсон Даяна Эдуардовна");
        right2.setRegistration("Долевая собственность, № 27-27-01/070/2009-626 от 30.07.2009, 36/100");
        flat.getRights().addAll(Arrays.asList(right1, right2));

        Address address = new Address();
        address.setRegion("27");
        address.setLocality(new Locality("г", "Хабаровск"));
        address.setStreet(new Street("ул", "Краснореченская"));
        address.setBuilding(new Building("д", "13"));
        address.setApartment(new Apartment("кв", "15"));

        flat.setAddress(address);

        assertThat(parsedFlat, is(flat));
    }

    /**
     * Test parsing consistent xml file with organization owner
     */
    @Test
    public void testParseConsistentFileWithOrganizationOwner() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(ORGANIZATION_OWNER);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        Flat parsedFlat = flatParser.getFlat();
        assertTrue(parsedFlat.isConsistent());

        Flat flat = new Flat();
        flat.setTotalArea(new BigDecimal("718"));

        Right right1 = new Right();
        right1.setOwner("Общество с ограниченной ответственностью \"Универсам\", ИНН: 2723113137");
        right1.setRegistration("Собственность, № 27-27/001-27/074/204/2015-7665/2 от 16.12.2015");
        flat.getRights().add(right1);

        Address address = new Address();
        address.setRegion("27");
        address.setLocality(new Locality("г", "Хабаровск"));
        address.setStreet(new Street("ул", "Краснореченская"));
        address.setBuilding(new Building("д", "9"));
        address.setApartment(new Apartment("пом", "I (1-31)"));

        flat.setAddress(address);

        assertThat(parsedFlat, is(flat));
    }

    /**
     * Test xml file with no flat
     */
    @Test(expected = FlatParserFailException.class)
    public void testNotFlat() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(NOT_FLAT);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        assertFalse(flatParser.isFlatParsed());
    }

    /**
     * Test xml file with no flat but with flat tag
     */
    @Test(expected = FlatParserFailException.class)
    public void testNotFlatWithFlatTag() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(NOT_FLAT_WITH_FLAT_TAG);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        assertFalse(flatParser.isFlatParsed());
    }

    /**
     * Test xml file without right
     */
    @Test
    public void testRightNotFound() throws FlatParserFailException {
        URL xml = FlatParserTest.class.getResource(RIGHT_NOT_FOUND);
        FlatParser flatParser = new FlatParser(new File(xml.getFile()));
        flatParser.parse();
        Flat parsedFlat = flatParser.getFlat();
        assertTrue(parsedFlat.getRights().stream().anyMatch(right -> right.isNoOwner() && right.isNoRegistration()));
    }

}
