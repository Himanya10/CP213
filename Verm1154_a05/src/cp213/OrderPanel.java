package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Himanya Verma
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Opens the system's print
     * dialog to print the current contents of the Order.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    try {
		// Create PrinterJob and configure it to print the order receipt
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		// Check if the user has a printer available and show the print dialog
		if (printerJob.printDialog()) {
		    // Set up the print task to print the order receipt
		    printerJob.setPrintable((graphics, pageFormat, pageIndex) -> {
			if (pageIndex > 0) {
			    return Printable.NO_SUCH_PAGE; // No more pages
			}
			// Print the order details at coordinates (100, 100)
			graphics.drawString(order.toString(), 100, 100);
			return Printable.PAGE_EXISTS;
		    });

		    // Start the print job
		    printerJob.print();
		}
	    } catch (PrinterException ex) {
		ex.printStackTrace();
		JOptionPane.showMessageDialog(null, "Failed to print order receipt.", "Error",
			JOptionPane.ERROR_MESSAGE);
	    }
	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
	private MenuItem item = null;

	QuantityListener(final MenuItem item) {
	    this.item = item;
	}

	@Override
	public void focusGained(java.awt.event.FocusEvent e) {
	    // No action needed
	}

	@Override
	public void focusLost(final FocusEvent e) {
	    // downcasting should be fine as long as JTextField are only ones with this
	    // listener
	    JTextField quantityText = (JTextField) e.getSource();
	    String quantityStr = quantityText.getText();
	    int quantity = 0;
	    try {
		quantity = Integer.parseInt(quantityStr);
		if (quantity < 0) {
		    quantity = 0;
		}
	    } catch (NumberFormatException error) {
		quantity = 0;
	    }

	    order.update(item, quantity);

	    subtotalLabel.setText(String.format("%.2f", order.getSubTotal().floatValue()));
	    taxLabel.setText(String.format("%.2f", order.getTaxes().floatValue()));
	    totalLabel.setText(String.format("%.2f", order.getTotal().floatValue()));

	    String qToString = String.valueOf(quantity);
	    quantityText.setText(qToString);
	}

    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {
	GridLayout gLayout = new GridLayout(menu.size() + 5, 3);
	setLayout(gLayout);

	JLabel itemHeading = new JLabel("Item");
	JLabel priceHeading = new JLabel("Price");
	JLabel quantityHeading = new JLabel("Quantity");

	this.add(itemHeading);
	this.add(priceHeading);
	this.add(quantityHeading);

	for (int i = 0; i < menu.size(); i++) {
	    MenuItem item = menu.getItem(i);
	    nameLabels[i] = new JLabel(item.getListing());
	    priceLabels[i] = new JLabel(item.getPrice().toString());
	    quantityFields[i] = new JTextField("0", 5);

	    this.add(nameLabels[i]);
	    this.add(priceLabels[i]);
	    this.add(quantityFields[i]);
	}

	JLabel subtotalHeading = new JLabel("Subtotal:");
	JLabel taxHeading = new JLabel("Tax:");
	JLabel totalHeading = new JLabel("Total:");

	this.add(subtotalHeading);
	this.add(new JLabel(""));
	this.add(subtotalLabel);
	this.add(taxHeading);
	this.add(new JLabel(""));
	this.add(taxLabel);
	this.add(totalHeading);
	this.add(new JLabel(""));
	this.add(totalLabel);
	this.add(new JLabel(""));
	this.add(printButton);

	this.setVisible(true);
    }

    /**
     * Register the widget listeners with the widgets.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	for (int i = 0; i < menu.size(); i++) {
	    MenuItem item = menu.getItem(i);
	    quantityFields[i].addFocusListener(new QuantityListener(item));
	}
    }
}
