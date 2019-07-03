package utilitarios;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.to.DisponibilidadDocente;



public class GeneradorPDF {
    
    public static void main(String[] args){
        try {
            GenerarReporteDisponibilidad(null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void GenerarReporteDisponibilidad(DisponibilidadDocente disponibilidad) throws FileNotFoundException {
        
        PdfDocument pdf = new PdfDocument(new PdfWriter("D:\\TEST"));
        try (Document document = new Document(pdf)) {
            Table table = new Table(3);
            Paragraph docTitle = new Paragraph("UCSC Direct - Direct Payment Form").setMarginRight(1);
            Cell cell = new Cell(1, 3).add(docTitle)
                .setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);
            cell = new Cell(2, 1)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            table.addCell("Cell 1.1");
            table.addCell(new Cell());
            table.addCell(new Cell()
                .setMargin(5));
            table.addCell(new Cell()
                .setPadding(5));
            document.add(table);
                        
        }
    }
}
