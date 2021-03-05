package com.piatekd.cvbuilder_v2.utils;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.piatekd.cvbuilder_v2.entity.*;
import com.piatekd.cvbuilder_v2.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class Generator {


//    public static final String FONT = "src/main/resources/font/Aller_Rg.ttf";
    public static final String FONT = "C:/Users/dwdptk/Downloads/cv-builder_v2/src/main/resources/font/Aller_Rg.ttf";
    private static PdfFont polishFont;
    private String fileName;

    @Value("${pdf.source}")
    private String src;

    @Value("${pdf.output}")
    private String dest;

    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private HttpSession session;



    public void generator(Person person) throws Exception {
        fileName = "resume-" + person.getFirstName() + person.getLastName() + ".pdf";
        String fullPath = dest + fileName;
        session.setAttribute("path", fullPath);
        File file = new File(fullPath);
        file.getParentFile().mkdirs();
        new Generator().manipulatePdf(src, person, fullPath);
    }

    protected void manipulatePdf(String src, Person person, String fullPath) throws Exception {
        PdfFont polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(fullPath));
        Document doc = new Document(pdfDoc);
        doc.setMargins(35f, 30f,70f,30f);
//        doc.setBottomMargin(40f);

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        Map<String, PdfFormField> fields = form.getFormFields();
        fields.get("full-name").setFont(polishFont).setValue(person.getFirstName() + " " + person.getLastName());
        fields.get("phone").setFont(polishFont).setValue(person.getPhoneNumber());
        fields.get("e-mail").setFont(polishFont).setValue(person.getEmail());
        fields.get("address1").setFont(polishFont).setValue(person.getAddress());
        fields.get("address2").setFont(polishFont).setValue(person.getCity() + ", " + person.getZipCode());


        if (person.getProfiles().size() != 0) {
            List<String> profiles = Arrays.asList("profile1", "profile2", "profile3");
            fields.get("profile0").setValue("Profiles").setFontSize(16);
            for (int i = 0; i < person.getProfiles().size(); i++) {
                fields.get(profiles.get(i)).setValue(person.getProfiles().get(i));
            }
        }
        fields.get("clauzule").setFont(polishFont).setValue(person.getClauzule());
        form.flattenFields();


        doc.setRenderer(new ExtraTableRenderer(doc));
        if (person.getAboutMe() != null) {
            doc.add(addAboutMe(person.getAboutMe()));
        }
        if (person.getExperienceSet().size() != 0) {
            doc.add(addExperience(person.getExperienceSet()));
        }
        if (person.getEducationSet().size() != 0) {
            doc.add(addEducation(person.getEducationSet()));
        }
        if (person.getLanguageSet().size() != 0) {
            doc.add(addLanguages(person.getLanguageSet()));
        }
        if (person.getAchievementSet().size() != 0) {
            doc.add(addAchievements(person.getAchievementSet()));
        }
        if (person.getSkills().size() != 0) {
            doc.add(addSkills(person.getSkills()));
        }
        if (person.getHobbies().size() != 0) {
            doc.add(addHobbies(person.getHobbies()));
        }
        doc.close();
    }

    public Table addAboutMe(String aboutMe) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{1}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("About Me"))));
        table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED_ALL).setBorder(Border.NO_BORDER).add(new Paragraph((aboutMe)).setFont(polishFont)));
        return table;
    }

    public Table addExperience(Set<Experience> experiences) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("Experience"))));
        for (Experience experience : experiences) {
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((experience.getPosition() + ", " + experience.getCompanyName() + ", " + experience.getPlace())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(9).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).add(new Paragraph((String.format("%02d", experience.getDateStarted().getMonthValue()) + "." + experience.getDateStarted().getYear() + "-" + String.format("%02d", experience.getDateEnded().getMonthValue()) + "." + experience.getDateEnded().getYear())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setBorder(Border.NO_BORDER).add(new Paragraph((experience.getShortDescription())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED_ALL).setBorder(Border.NO_BORDER));
        }
        return table;
    }

    public Table addEducation(Set<Education> educations) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("Education"))));
        for (Education education : educations) {
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((education.getCourse() + ", " + education.getDegree() + ", " + education.getSchoolName())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(9).setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).add(new Paragraph((String.format("%02d", education.getStartedSchool().getMonthValue()) + "." + education.getStartedSchool().getYear() + "-" + String.format("%02d", education.getFinishedSchool().getMonthValue()) + "." + education.getFinishedSchool().getYear())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED).setBorder(Border.NO_BORDER).add(new Paragraph((education.getAdditionalInfo())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED_ALL).setBorder(Border.NO_BORDER));
        }
        return table;
    }

    public Table addLanguages(Set<ForeignLanguage> languages) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 1, 1}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("Languages"))));
        for (ForeignLanguage language : languages) {
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((language.getLanguage() + " - " + language.getProficiency())).setFont(polishFont)));
        }
        return table;
    }

    public Table addAchievements(Set<Achievement> achievements) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 4}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("Achievements"))));
        for (Achievement achievement : achievements) {
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((achievement.getName())).setFont(polishFont)));
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((achievement.getDescription())).setFont(polishFont)));
        }
        return table;
    }

    public Table addSkills(List<String> skills) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 1, 1, 1}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("Skills"))));
        for (String skill : skills) {
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((skill)).setFont(polishFont)));
        }
        return table;
    }

    public Table addHobbies(List<String> hobbies) {
        PdfFont polishFont = null;
        try {
            polishFont = PdfFontFactory.createFont(FONT, "Cp1250", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 1, 1, 1}));
        table.setMarginLeft(215f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setFontSize(14).setBold().setBorder(Border.NO_BORDER).add(new Paragraph(("Hobbies"))));
        for (String hobby : hobbies) {
            table.addCell(new Cell().setFontSize(11).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).add(new Paragraph((hobby)).setFont(polishFont)));
        }
        return table;
    }

    protected static class ExtraTableRenderer extends DocumentRenderer {

        public ExtraTableRenderer(Document document) {
            super(document);
        }

        // If renderer overflows on the next area, iText uses getNextRender() method to create a renderer for the overflow part.
        // If getNextRenderer isn't overriden, the default method will be used and thus a default rather than custom
        // renderer will be created
        @Override
        public IRenderer getNextRenderer() {
            return new ExtraTableRenderer(document);
        }

        @Override
        protected LayoutArea updateCurrentArea(LayoutResult overflowResult) {
            LayoutArea area = super.updateCurrentArea(overflowResult);
            if (area.getPageNumber() == 1) {
                area.getBBox().decreaseHeight(35f);
            }
            if (area.getPageNumber() == 1) {
                document.setLeftMargin(-185f);
            }

            return area;
        }

    }
}
