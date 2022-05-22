package pl.umk.mat.planner.toPDF;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class PdfGenerator {

    public ByteArrayInputStream exportPdf(String templateName) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
//        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);

        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream, false);
            renderer.finishPDF();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (DocumentException e) {
            throw new DocumentException(e);
        }

        return byteArrayInputStream;
    }
//    public org.jsoup.nodes.Document getDoc() throws IOException {
////        File inputHTML = new File("src/main/resources/templates/generator/toPDF.html"); //Get input html file
//        File inputHTML = ResourceUtils.getFile("classpath:templates/admin/home.html");
////        String content = new String(Files.readAllBytes(inputHTML.toPath()));
////        System.out.println(content);
//
//        org.jsoup.nodes.Document document = Jsoup.parse(inputHTML, "UTF-8");
//        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
//        return document;
//    }
//
//    public void ExportGeneratedPDF() throws IOException {
//        String outputPdf = "plan.pdf";
//
//        org.jsoup.nodes.Document doc = getDoc();
//
//        try (OutputStream os = new FileOutputStream(outputPdf)) {
//            PdfRendererBuilder builder = new PdfRendererBuilder();
//            builder.withUri(outputPdf);
//            builder.toStream(os);
//            builder.withW3cDocument(new W3CDom().fromJsoup(doc), "/export/");
//            ;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
