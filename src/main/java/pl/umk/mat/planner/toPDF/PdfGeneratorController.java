package pl.umk.mat.planner.toPDF;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller

public class PdfGeneratorController {

    @GetMapping("/generator")
    public String ToPDF(PdfGenerator $gen) throws IOException {
        $gen.ExportGeneratedPDF();
        return "/generator/toPDF";
    }


}
