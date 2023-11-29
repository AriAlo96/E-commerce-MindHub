package MindHub.ecommerce.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PurchasePDF {

    private Purchase purchase;
    private List<PurchaseCream> purchaseCreamList;
    private List<PurchaseFlavoring> purchaseFlavoringsList;
    private List<PurchaseFragance> purchaseFragancesList;

    public PurchasePDF(Purchase purchase, List<PurchaseCream> purchaseCreamList, List<PurchaseFlavoring> purchaseFlavoringsList, List<PurchaseFragance> purchaseFragancesList) {
        this.purchase = purchase;
        this.purchaseCreamList = purchaseCreamList;
        this.purchaseFlavoringsList = purchaseFlavoringsList;
        this.purchaseFragancesList = purchaseFragancesList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

        cell.setPhrase(new Phrase("Product", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("subtotal", font));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {

        if (purchaseCreamList != null) {
            for (PurchaseCream cream : purchaseCreamList) {
                table.addCell(cream.getCream().getName());
                table.addCell(String.valueOf(cream.getQuantity()));
                table.addCell((String.format(String.valueOf(cream.getSubtotal()), "$0,0.00")));
            }

        }
        if (purchaseFragancesList != null) {
            for (PurchaseFragance fragance : purchaseFragancesList) {
                table.addCell(fragance.getFragance().getName());
                table.addCell(String.valueOf(fragance.getQuantity()));
                table.addCell((String.format(String.valueOf(fragance.getSubtotal()), "$0,0.00")));
            }
        }
        if (purchaseFlavoringsList != null) {
            for (PurchaseFlavoring flavoring : purchaseFlavoringsList) {
                table.addCell(flavoring.getFlavoring().getName());
                table.addCell(String.valueOf(flavoring.getQuantity()));
                table.addCell(String.format(String.valueOf(flavoring.getSubtotal()), "$0,0.00"));
            }
        }
    }

    public void usePDFExport(HttpServletResponse response) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();

        // Estilos de fuentes
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font detailsFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Título
        Paragraph title = new Paragraph("Your purchase", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(10);
        doc.add(title);

        doc.add(new Paragraph("Purchase Id: " + purchase.getId(), detailsFont));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        // Encabezado de la tabla
        writeTableHeader(table);

        // Contenido de la tabla
        writeTableData(table);

        doc.add(table);
        doc.close();

    }

}