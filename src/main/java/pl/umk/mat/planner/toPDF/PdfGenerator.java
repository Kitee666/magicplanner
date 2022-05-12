package pl.umk.mat.planner.toPDF;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class PdfGenerator {
    public org.jsoup.nodes.Document getDoc() throws IOException {
//        File inputHTML = new File("src/main/resources/templates/generator/toPDF.html"); //Get input html file
        File inputHTML = ResourceUtils.getFile("classpath:templates/admin/home.html");
//        String content = new String(Files.readAllBytes(inputHTML.toPath()));
//        System.out.println(content);

        org.jsoup.nodes.Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document;
    }

    public void ExportGeneratedPDF() throws IOException {
        String outputPdf = "plan.pdf";

        org.jsoup.nodes.Document doc = getDoc();

        try (OutputStream os = new FileOutputStream(outputPdf)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(outputPdf);
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(doc), "/export/");
            builder.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
