package MindHub.ecommerce.models;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PurchasePDF {

    private Purchase purchase;
   // private List<PurchaseCream> purchaseCreamList;
   // private List<PurchaseFlavoring> purchaseFlavoringsList;
  //  private List<PurchaseFragance> purchaseFragancesList;


    public PurchasePDF(Purchase purchase) {
        this.purchase = purchase;
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

        List<PurchaseCream> purchaseCreamList = new ArrayList<>(purchase.getPurchaseCreams());
        List<PurchaseFlavoring> purchaseFlavoringList = new ArrayList<>(purchase.getPurchaseFlavorings());
        List<PurchaseFragance> purchaseFraganceList = new ArrayList<>(purchase.getPurchaseFragances());

        if (purchaseCreamList != null) {
            for (PurchaseCream cream : purchaseCreamList) {
                table.addCell(cream.getCream().getName());
                table.addCell(String.valueOf(cream.getQuantity()));
                table.addCell((String.format(String.valueOf(cream.getSubtotal()), "$0,0.00")));
            }

        }
        if (purchaseFraganceList != null) {
            for (PurchaseFragance fragance : purchaseFraganceList) {
                table.addCell(fragance.getFragance().getName());
                table.addCell(String.valueOf(fragance.getQuantity()));
                table.addCell((String.format(String.valueOf(fragance.getSubtotal()), "$0,0.00")));
            }
        }
        if (purchaseFlavoringList != null) {
            for (PurchaseFlavoring flavoring : purchaseFlavoringList) {
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