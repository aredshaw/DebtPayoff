/** Calculate compound interest
 *  to help you alleviate credit card debt.
 *  @author Aaron K. Redshaw
 *  @author www.aaronkredshaw.com
 *  @author www.linkedin.com/in/aaron-redshaw/
 *  @version 1.30 
 *  Updated 01/28/2019
 */
// TODO:
// Add Javadoc when done

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;

public class DebtPayoff {
/**
 * 
 * @param args part of main method to get things rolling.
 */
    public static void main(String[] args) {
        DebtPayoffFrame frame = new DebtPayoffFrame(); //creates a frame object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the close action. It exits the program when closed.
        frame.setVisible(true); //so you can see the frame

    }

}

class DebtPayoffFrame extends JFrame {

    private static final int WIDE = 600; //the width of the window
    private static final int HIGH = 500; //the height of the window
    private static final long serialVersionUID = 1L; //if a serialVersionUID is not explicitly given, a value is generated automatically, which is not good.

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
    private final JComboBox<String> dayOfPayment;

    private final JTextArea outPutBox;
    private final JScrollPane scrollPane;

    private final JLabel dollarSign3;

    public DebtPayoffFrame() { //this is a constructor. It has the same name as the class and has no parameters

        setResizable(true); //the window can be resizable or fixed. Either true or false.

        setTitle("DebtPayoff"); //the title at the top of the program.
        setSize(WIDE, HIGH); //this, combined with the private static final int WIDE = 600 and HIGH lines above, sets the beginning dimensions of the UI.
        //WIDE and HIGH are variables. setSize is a method.

        Container contentPane = getContentPane(); //this gets the reference of the content pane.
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);

        Toolkit kit = Toolkit.getDefaultToolkit(); //Toolkit is part of AWT (Abstract Window Toolkit)
        Dimension screenSize = kit.getScreenSize(); //this figures out the screen size.
        int screenHeight = screenSize.height; //It figures out the height of the screen
        int screenWidth = screenSize.width; //It figures out the width of the screen

        // center frame using the dimensions figured out above with screenHeight and screenWidth
        setLocation(screenWidth / 3, screenHeight / 4); //dividing by 4 about centers it.

        // set frame icon image. Make sure to package it with the program
        Image img = kit.getImage("images/DebtPayoff.png");
        setIconImage(img);

        // construct components
        /**
         *
         * ImageIcon logoBanner = new ImageIcon("logo3.gif"); JScrollPane logo =
         * new JScrollPane(new JLabel(logoBanner));
         */
        //these are all constructors since they do not use void.
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
        dayOfPayment = new JComboBox<String>(new String[]{ //the dropdown box with the dates of the month
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
        });

        dollarSign3 = new JLabel("$");

        /**
         * create box and make it scrollable with default text
         */
        String attempt = "  Ouput will appear here."; //default message in the output box
        outPutBox = new JTextArea(attempt);
        scrollPane = new JScrollPane(outPutBox); //making the box scrollable

        /**
         * Add action to submit button
         */
        JButton submitButton = new JButton("Submit"); //submit button
        ActionListener listener = new SubmitAction();
        submitButton.addActionListener(listener);

        /**
         * set components in GridBag with Constraints
         */
        GridBagConstraints constraints = new GridBagConstraints(); //The layout of the UI

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 0; //adds space from the left
        constraints.weighty = 0; //adds space from the bottom
        constraints.ipadx = 2; //padding between the "$" and the text area
        constraints.ipady = 2; //how tall the entry boxes are
        constraints.insets = new Insets(5, 0, 1, 1);

        /**
         * This would be for a logo, see also above and below for related code
         *
         * constraints.anchor = GridBagConstraints.CENTER; constraints.fill =
         * GridBagConstraints.BOTH; add(logo, constraints, 0, 0, 3, 1);
         */
        //Here is the placement of each item on the UI using the GridBagConstraints
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
     * This is how you layout the GUI.
     * @param x=rows, y=columns, w=hgap, h=vgap.
     * Although it worked for me, with a lot of trial and error,
     * this is a very complex way to create your GUI by hand.
     * In the future, it might be best to use something else,
     * or use NetBeans, which I now know gives you an easy
     * interface to do this.
     */
    private void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        getContentPane().add(c, constraints);
    }

    /**
     * the action for pressing the submit button
     */
    private class SubmitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            /**
             * Make Strings out of the input objects
             */
            String interestRateString = interestRate.getText();

            /**
             * Strings converted into Numbers
             */
            double total = Double.parseDouble(totalToPay.getText().trim());
            double interest = Double.parseDouble(interestRate.getText().trim());
            double current = Double.parseDouble(currentPayment.getText().trim());
            int dayOfPaymentText = Integer.parseInt((String) dayOfPayment.getSelectedItem());

            /**
             * Make currency output format
             */
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String totalToPayCurrency = formatter.format(total);
            String currentPaymentCurrency = formatter.format(current);

            /**
             * set line wrap to on
             */
            outPutBox.setLineWrap(true);

            /**
             * Gregorian Calendar, so the dates will be accurate
             */
            GregorianCalendar now = new GregorianCalendar();
            DateFormat localFormat = DateFormat.getDateInstance();
            Date date = now.getTime();

            /**
             * print beginning account information to the screen
             */
            outPutBox.setText("Today, " + localFormat.format(date) + ", you have:\n");//Calendar
            outPutBox.append(totalToPayCurrency + " left to be debt free" + "\n");
            outPutBox.append("at %" + interestRateString + " interest,\n");
            outPutBox.append("paying " + currentPaymentCurrency + " per month." + "\n\n");

            /**
             * call the mathematics method
             */
            SubmitAction math = new SubmitAction();
            math.mathematics();
        }

        /**
         * Do some math, The basic formula goes like this:
         *
         * As long as payAmount > 0... amount to pay off = totalToPay interest
         * rate =	interestRate amount per payment =	currentPayment
         *
         * interestAmount = totalToPay * (.01 * interestRate) totalToPay =
         * totalToPay + interestAmount - currentPayment
         */
        private void mathematics() {
            /**
             * Strings converted into Numbers
             */
            double total = Double.parseDouble(totalToPay.getText().trim());
            double interest = Double.parseDouble(interestRate.getText().trim());
            double current = Double.parseDouble(currentPayment.getText().trim());

            /**
             * Gregorian Calendar
             */
            GregorianCalendar now = new GregorianCalendar();
            DateFormat localFormat = DateFormat.getDateInstance();
            Date date = now.getTime();
            int dayOfPaymentText = Integer.parseInt((String) dayOfPayment.getSelectedItem());

            /**
             * begin basic math functions
             */
            double totalInterest = 0;
            int count = 0;

            while (total > 0) {
                /**
                 * counting years and months with correct syntax
                 */
                count++;
                double interestAmount = total * (.01 * interest / 12);
                total += interestAmount - current;

                /**
                 * try for input error
                 */
                if (interestAmount >= current) {
                    NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    String interestAmountCurrency = formatter.format(interestAmount);
                    outPutBox.append("The payment amount you have entered is less than \nor equal to the interest for the first payment.\nAt this rate, you will never finish payment of this debt.\n\nPlease enter another payment amount.  ");
                    outPutBox.append("Your interest amount is " + interestAmountCurrency + "\n\n");
                    break;
                } else {

                    if (total < 0) {
                        total = 0;
                    }

                    /**
                     * correct syntax
                     */
                    if (count < 12) // less than one year
                    {
                        if (count == 1) {
                            //if todays date is larger than the day chosen for monthly payments
                            if (now.get(Calendar.DAY_OF_MONTH) > dayOfPaymentText) {
                                now.add(Calendar.MONTH, 1);//add one month since payment does not come this month
                                now.set(Calendar.DAY_OF_MONTH, dayOfPaymentText);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + count + " month.  You will have:\n");
                            } else {

                                now.set(Calendar.DAY_OF_MONTH, dayOfPaymentText);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + count + " month.  You will have:\n");
                            }
                        } else {
                            now.add(Calendar.MONTH, 1);//add one month
                            outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + count + " months.  You will have:\n");
                        }
                    } else // one year or more
                    {
                        int yr = count / 12;
                        int mo = count % 12;
                        if (yr == 1) // one year
                        {
                            if (mo < 1) // one year, no months
                            {
                                now.add(Calendar.MONTH, 1);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + " year.  You will have:\n");
                            } else if (mo == 1) // one year, one month
                            {
                                now.add(Calendar.MONTH, 1);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  year, " + mo + " month.  You will have:\n");
                            } else // one year, ...months
                            {
                                now.add(Calendar.MONTH, 1);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  year, " + mo + " months.  You will have:\n");
                            }

                        } else // more than one year
                        {
                            if (mo < 1) // more than one year, no months
                            {
                                now.add(Calendar.MONTH, 1);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + " years.  You will have:\n");
                            } else if (mo == 1) // more than one year, one month
                            {
                                now.add(Calendar.MONTH, 1);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  years, " + mo + " month.  You will have:\n");
                            } else // more than one year, ...months
                            {
                                now.add(Calendar.MONTH, 1);//add one month
                                outPutBox.append("On " + localFormat.format(now.getTime()) + ", it will have been " + yr + "  years, " + mo + " months.  You will have:\n");
                            }
                        }
                    }
                }

                /**
                 * Make currency output format
                 */
                totalInterest += interestAmount;
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String interestAmountCurrency = formatter.format(interestAmount);
                String totalToPayCurrency = formatter.format(total);
                String totalInterestCurrency = formatter.format(totalInterest);
                // String ammountFCurrency = formatter.format(ammountFeesText);

                /**
                 * Print results
                 */
                outPutBox.append(totalToPayCurrency + " left to be debt free." + "\n");
                outPutBox.append("You will have paid " + interestAmountCurrency + " in interest for this payment.\n");
                outPutBox.append(totalInterestCurrency + " will have been paid in interest overall.\n\n");

            }
        }
    }
}
