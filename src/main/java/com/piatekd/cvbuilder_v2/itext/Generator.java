package com.piatekd.cvbuilder_v2.itext;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
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
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Map;

@Component
public class Generator {
    public static String DEST = "./src/main/resources/pdfs/resume";

    public static String SRC = "./src/main/resources/pdfs/resume-template.pdf";

    @Autowired
    private PersonService personService;

    @Autowired
    private HttpSession session;


    public void generator(Person person) throws Exception {
        String dest1 = "C:\\Users\\dwdptk\\Downloads\\cv-builder_v2\\src\\main\\resources\\pdfs\\resume-DawidPiatek.pdf";
        String dest = DEST + "-" + person.getFirstName() + person.getLastName() + ".pdf";
        session.setAttribute("path", dest1);
        File file = new File(dest1);
        file.getParentFile().mkdirs();
        new Generator().manipulatePdf(dest1, person);
    }

    protected void manipulatePdf(String dest1, Person person) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest1));
        Document doc = new Document(pdfDoc);

        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        Map<String, PdfFormField> fields = form.getFormFields();
        fields.get("full-name").setValue(person.getFirstName() + " " + person.getLastName());
        fields.get("phone").setValue(person.getPhoneNumber());
        fields.get("e-mail").setValue(person.getEmail());
        fields.get("address1").setValue(person.getAddress());
        fields.get("address2").setValue(person.getCity() + ", " + person.getZipCode());
        //                  Ustawione na sztywno
        fields.get("profile1").setValue(person.getProfiles().get(0));
        fields.get("profile2").setValue(person.getProfiles().get(1));
        //
        fields.get("clauzule").setValue(person.getClauzule());
        form.flattenFields();


        Table table = new Table(UnitValue.createPercentArray(new float[]{1}));
        table.setMarginLeft(220f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(("Skills"))));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(("ochronie danych osobowych (Dz. Ustaw z 2018, poz. 1000) oraz zgodnie z fsv kdfmklv jfbdkjvdfjv jfdj vndf bjkdfkj djkfv ndfjhn jkdfjk dfjh jbdf df"))));
        table.addCell(new Cell().setBorder(Border.NO_BORDER).add(new Paragraph(("ochronie danych osobowychdfjk dfjh jbdf df"))));
        /*Table table = new Table(UnitValue.createPercentArray(new float[] {1, 15}));
        table.setMarginLeft(220f);
        table.setWidth(UnitValue.createPercentValue(100));
        table.addHeaderCell("#");
        table.addHeaderCell("description");
        for (int i = 1; i <= 15; i++) {
            table.addCell(String.valueOf(i));
            table.addCell("test " + i);

            //original one
        }*/

        // The custom renderer decreases the first page's area.
        // As a result, there is not overlapping between the table from acroform and the new one.
        doc.setRenderer(new ExtraTableRenderer(doc));
        doc.add(table);

        doc.close();
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
                area.getBBox().decreaseHeight(35);
            }

            return area;
        }

    }
}
