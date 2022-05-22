package pl.umk.mat.planner.toPDF;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

@Controller

public class PdfGeneratorController {

    @GetMapping("/generator")
    public String ToPDF(PdfGenerator gen, HttpServletResponse response) throws IOException {
//        $gen.ExportGeneratedPDF();
        ByteArrayInputStream exportedData = gen.exportPdf("generator/toPDF");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());
        return "/generator/toPDF";
    }
}
