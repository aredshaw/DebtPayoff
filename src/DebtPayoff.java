/*
 *  Written by Aaron K. Redshaw
 *  Updated 09/14/2018
 */
package debtpayoff;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DebtPayoff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
	DebtPayoffFrame frame = new DebtPayoffFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.show();
    
    }
    
}

class DebtPayoffFrame extends JFrame
{
    
    
    public static final int WIDE = 600;
    public static final int HIGH = 500;
    
    private Color colorBackground;
    //private ImageIcon logoBanner;

    private final JLabel totalToPayLabel;
    private final JLabel dollarSign1;
    private final JTextField totalToPay;

    private final JLabel interestRateLabel;
    private final JTextField interestRate;
    private final JLabel percentSign;

    private final JLabel currentPaymentLabel;
    private final JLabel dollarSign2;
    private final JTextField currentPayment;

    private final JLabel dayOfPaymentLabel;
    private final JComboBox dayOfPayment;

    private final JTextArea outPutBox;
    private final JScrollPane scrollPane;

    private final JLabel dollarSign3;

    public DebtPayoffFrame() {
        setTitle("DebtPayoff");
        setSize(WIDE, HIGH);
        
        Container contentPane = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);
        
        
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        // center frame
        setLocation(screenWidth / 4, screenHeight / 4);
        
        // set frame icon image
        Image img = kit.getImage("images/DebtPayoff.png");
        setIconImage(img);
        
        
        
        
        // construct components
        
        /**
         * If you wanted to have an icon for this called logo3.gif, you would put it here
         * 
         * ImageIcon logoBanner = new ImageIcon("logo3.gif");
         * JScrollPane logo = new JScrollPane(new JLabel(logoBanner));
         */
        
        
        totalToPayLabel = new JLabel("  Total to Pay off Debt");
        dollarSign1 = new JLabel("$");
        totalToPay = new JTextField(5);
        
        interestRateLabel = new JLabel("  Interest Rate");
        interestRate = new JTextField(5);
        percentSign = new JLabel("%    ");
        
        currentPaymentLabel = new JLabel("  Current Payment Amount    ");
        dollarSign2 = new JLabel("$");
        currentPayment = new JTextField(5);
        
        dayOfPaymentLabel = new JLabel("Day of Monthly Payment    ");
        dayOfPayment = new JComboBox(new String[]
        {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        });
        
        
        dollarSign3 = new JLabel("$");
        
        /**
         * create box and make it scrollable with default text
         */
        String attempt = "  Ouput will appear here.";
        outPutBox = new JTextArea(attempt);
        scrollPane = new JScrollPane(outPutBox);
        
        /**
         * Add action to submit button
         */
        JButton submitButton = new JButton("Submit");
        ActionListener listener = new SubmitAction();
        submitButton.addActionListener(listener);
        
        /**
         * set components in GridBag with Constraints
         */
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.insets = new Insets(5, 0, 1, 1);
        
        /**
         * This would be for a logo, see also above and below for related code
         * 
         * constraints.anchor = GridBagConstraints.CENTER;
         * constraints.fill = GridBagConstraints.BOTH;
         * add(logo, constraints, 0, 0, 3, 1);
         */
        
        add(totalToPayLabel, constraints, 0, 0, 1, 1);
        add(dollarSign1, constraints, 1, 0, 1, 1);
        add(totalToPay, constraints, 2, 0, 1, 1);
        constraints.weightx = 0;
        constraints.weighty = 0;
        
        add(interestRateLabel, constraints, 0, 1, 1, 1);
        add(interestRate, constraints, 2, 1, 1, 1);
        add(percentSign, constraints, 3, 1, 1, 1);
        constraints.weightx = 0;
        constraints.weighty = 0;
        
        add(currentPaymentLabel, constraints, 0, 2, 1, 1);
        add(dollarSign2, constraints, 1, 2, 1, 1);
        add(currentPayment, constraints, 2, 2, 1, 1);
        constraints.weightx = 0;
        constraints.weighty = 0;
        
        add(dayOfPaymentLabel, constraints, 4, 2, 1, 1);
        add(dayOfPayment, constraints, 5, 2, 1, 1);
        constraints.weightx = 0;
        constraints.weighty = 0;
        
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 0;
        add(submitButton, constraints, 0, 5, 6, 1);
        
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 100;
        constraints.weighty = 100;
        add(scrollPane, constraints, 0, 6, 6, 4);
    }

    /**
    for adding constraints
     */
    private void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
	constraints.gridy = y;
	constraints.gridwidth = w;
	constraints.gridheight = h;
	getContentPane().add(c, constraints);
    }

    /**
       the action for pressing the submit button
    */
    private class SubmitAction implements ActionListener
    {
        @Override
	public void actionPerformed(ActionEvent event)
	{
	    /**
	       Make Strings out of the imput objects
	    */
	    String interestRateString = interestRate.getText();

	    /**
	       Strings converted into Numbers
	    */
	    double total = Double.parseDouble(totalToPay.getText().trim());
	    double interest = Double.parseDouble(interestRate.getText().trim());
	    double current = Double.parseDouble(currentPayment.getText().trim());
	    int dayOfPaymentText = Integer.parseInt((String)dayOfPayment.getSelectedItem());

	    /**
	       Make currency output format
	    */
	    NumberFormat formatter = NumberFormat.getCurrencyInstance();
	    String totalToPayCurrency = formatter.format(total);
	    String currentPaymentCurrency = formatter.format(current);

	    /**
	       set line wrap to on
	    */
	    outPutBox.setLineWrap(true);

	    /**
	       Gregorian Calendar
	    */
	    GregorianCalendar now = new GregorianCalendar();
	    DateFormat localFormat = DateFormat.getDateInstance();
	    Date date = now.getTime();
	    

	    /**
	       print beginning account information to the screen
	    */
	    outPutBox.setText("Today, " + localFormat.format( date ) + " you have:\n");//Calendar
	    outPutBox.append(totalToPayCurrency + " left to be debt free." + "\n");
	    outPutBox.append("At %" + interestRateString + "\n");
	    outPutBox.append("Paying " + currentPaymentCurrency + " per month." + "\n\n");

	    /**
	       call the mathematics method
	    */
	    SubmitAction math = new SubmitAction();
	    math.mathmatics();
	}

	    /**
	       Do some Math
	       The basic formula goes like this:

	       As long as payAmount > 0...
	       amount to pay off = 	totalToPay
	       interest rate =		interestRate
	       amount per payment =	currentPayment
	       
	       interestAmount = totalToPay * (.01 * interestRate)
	       totalToPay = totalToPay + interestAmount - currentPayment
	    */
	public void mathmatics()
	{
	    /**
	       Strings converted into Numbers
	    */
	    double total = Double.parseDouble(totalToPay.getText().trim());
	    double interest = Double.parseDouble(interestRate.getText().trim());
	    double current = Double.parseDouble(currentPayment.getText().trim());

	    /**
	       Gregorian Calendar
	    */
	    GregorianCalendar now = new GregorianCalendar();
	    DateFormat localFormat = DateFormat.getDateInstance();
	    Date date = now.getTime();
	    int dayOfPaymentText = Integer.parseInt((String)dayOfPayment.getSelectedItem());

	    /**
	       begin basic math functionsy
	    */
	    double totalInterest = 0;
	    int count = 0;

	    while (total > 0)
		{
		    /**
		       counting years and months with correct syntax
		    */
		    count++;
		    double interestAmount = total * (.01 * interest/12);
		    total += interestAmount - current;

		    /**
		       try for input error
		    */
		    if (interestAmount >= current)
			{
			    NumberFormat formatter = NumberFormat.getCurrencyInstance();
			    String interestAmountCurrency = formatter.format(interestAmount);
			    outPutBox.append("The payment amount you have entered is less than \nor equal to the interest for the first payment.\nAt this rate, you will never finish payment of this debt.\n\nPlease enter another payment amount.  ");
			    outPutBox.append("Your interest amount is " + interestAmountCurrency + "\n\n");
			    break;
			}
		    else{
		    
			if (total < 0)
			    {
				total = 0;
			    }
			
			
			/**
			   correct syntax
			*/
			if (count < 12) // less than one year
			    {
				if (count == 1)
				    {
					//if todays date is larger than the day chosen for monthly payments
					if (now.get(Calendar.DAY_OF_MONTH) > dayOfPaymentText)
					{
					    now.add(Calendar.MONTH, 1);//add one month since payment does not come this month
					    now.set(Calendar.DAY_OF_MONTH, dayOfPaymentText);//add one month
					    outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + count + " month.  You will have:\n");
					}
					    else
					{
					    
					    now.set(Calendar.DAY_OF_MONTH, dayOfPaymentText);//add one month
					    outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + count + " month.  You will have:\n");
					}
				    }
				else
				    {
					now.add(Calendar.MONTH, 1);//add one month
					outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + count + " months.  You will have:\n");
				    }
			    }
			else // one year or more
			    {
				int yr = count/12;
				int mo = count % 12;
				if (yr == 1) // one year
				    {
					if (mo < 1)  // one year, no months
					    {
						now.add(Calendar.MONTH, 1);//add one month
						outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + " year.  You will have:\n");
					    }
					else if (mo == 1)  // one year, one month
					    {
						now.add(Calendar.MONTH, 1);//add one month
						outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  year, " + mo + " month.  You will have:\n");
					    }
					else  // one year, ...months
					    {
						now.add(Calendar.MONTH, 1);//add one month
						outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  year, " + mo + " months.  You will have:\n");
					    } 
					
				    }
				else  // more than one year
				    {
					if (mo < 1)  // more than one year, no months
					    {
						now.add(Calendar.MONTH, 1);//add one month
						outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + " years.  You will have:\n");
					    }
					else if (mo == 1)  // more than one year, one month
					    {
						now.add(Calendar.MONTH, 1);//add one month
						outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  years, " + mo + " month.  You will have:\n");
					    }
					else  // more than one year, ...months
					    {
						now.add(Calendar.MONTH, 1);//add one month
						outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  years, " + mo + " months.  You will have:\n");
					    } 
				    }
			    }
		    }

		    /**
		       Make currency output format
		    */
		    totalInterest += interestAmount;
		    NumberFormat formatter = NumberFormat.getCurrencyInstance();
		    String interestAmountCurrency = formatter.format(interestAmount);
		    String totalToPayCurrency = formatter.format(total);
		    String totalInterestCurrency = formatter.format(totalInterest);
		    // String ammountFCurrency = formatter.format(ammountFeesText);

		    /**
		       Print results
		    */
		    outPutBox.append(totalToPayCurrency + " left to be debt free." + "\n");
		    outPutBox.append("You will have paid " + interestAmountCurrency + " in interest for this payment.\n");
		    outPutBox.append(totalInterestCurrency + " will have been paid in interest overall.\n\n");

		}
	}
    }
}
