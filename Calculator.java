import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;


//Only Shalala. no function
class TToing extends Thread {
	JLabel label;
	TToing(JLabel label) {
		this.label = label;
	}
	public void run() {
		while(true) {
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(label.getText().equals("21621901"))
				label.setText("21621904");
			else if(label.getText().equals("21621904"))
				label.setText("21621901");
		}
	}
}

class Parser implements ActionListener {
	JButton button;
	JTextField text;
	double d;
	int i;
	Parser(JButton button, JTextField text) {
		this.button = button;
		this.text = text;
		this.d = 0;
		this.i = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String arp = text.getText();
		if(button.getText().equals("parseINT")) {
			try {
				d = Double.valueOf(arp);
				i = (int) Math.round(d);
				text.setText(String.valueOf(i));
			}
			catch(Exception er) {
				
			}
		}
		else if(button.getText().equals("x.1")) {
			try {
				if(!arp.contains(".")) {
					text.setText(text.getText().concat(".1"));
				}
				else if(arp.charAt(arp.length() - 1) == '.') {
					text.setText(arp.concat("1"));
				}
			}
			catch(Exception er2) {
				
			}
		}
	}
	
}

class SuperStack {
	static int swordOfSurt(char des, char obj) {
		if(obj == '-' || obj == '+') {
			if(des == '-' || des == '+') {
				return 3;
			}
			else if(des == '*' || des == '/') {
				return 1;
			}
		}
		else if(obj == '*' || obj == '/') {
			if(des == '-' || des == '+') {
				return 2;
			}
			else if(des == '*' || des == '/') {
				return 3;
			}
		}
		return 1;
	}
	private String onebone;
	Stack<Double> dstack;
	Vector<Object> superstk;
	Stack<Character> cstack;
	char cache;
	public SuperStack(String stringed) {
		this.onebone = stringed;
		dstack = new Stack<Double>();
		superstk = new Vector<Object>();
		cstack = new Stack<Character>();
		cache = ' ';
	}
	void valueOfOperator() throws Exception {
		// 1 = in pair // 2 = out pair after // 3 = normal
		char[] charr = onebone.toCharArray();
		char[] buffer = new char[onebone.length()]; 
		String register;
		int n = 0;
		int pair = 3;
		for(int i = 0;i < charr.length;i++) {
			if(charr[i] == '(')
				pair = 1;
			else {
				if(pair == 1) {
					if(charr[i] != ')')
						buffer[n++] = charr[i];
					else {
						register = String.valueOf(buffer);
						superstk.add(Double.parseDouble(register));
						n = 0;
						pair = 2;
						buffer = new char[onebone.length()];
					}
				}
				else {
					if(charr[i] == '/' || charr[i] == '+' || charr[i] == '*' ||
							charr[i] == '-') {
						register = String.valueOf(buffer);
						if(pair == 2) {
							pair = 3;
							if(cstack.isEmpty())
								cstack.push(charr[i]);
							else {    //stack is not empty
								try {
									while(SuperStack.swordOfSurt(charr[i], cstack.peek()) != 1) {
										superstk.add(cstack.pop());
									}
									cstack.push(charr[i]);
								}
								catch(EmptyStackException e) {
									cstack.push(charr[i]);
								}
							}
							continue;
						}
						superstk.add(Double.parseDouble(register));
						n = 0;
						buffer = new char[onebone.length()];
						if(cstack.isEmpty())
							cstack.push(charr[i]);
						else {    //stack is not empty
							try {
								while(SuperStack.swordOfSurt(charr[i], cstack.peek()) != 1) {
									superstk.add(cstack.pop());
								}
								cstack.push(charr[i]);
							}
							catch(EmptyStackException e) {
								cstack.push(charr[i]);
							}
						}
					}
					else
						buffer[n++] = charr[i];
				}
			}
		}
		char[] emter = new char[onebone.length()];
		String emtreg = String.valueOf(emter);
		register = String.valueOf(buffer);
		if(register.equals(emtreg)) {
			while(!cstack.isEmpty()) {
				superstk.add(cstack.pop());
			}
			return;
		}
		superstk.add(Double.parseDouble(register));
		while(!cstack.isEmpty()) {
			superstk.add(cstack.pop());
		}
	}
	double resultation() {
		double op1 = 0, op2 = 0;
		double opr = 0;
		int inum = superstk.size();
		for(int i = 0;i < inum;i++) {
			Object tt = superstk.remove(0);
			if(tt.equals(Character.valueOf('/')) || 
					tt.equals(Character.valueOf('-')) ||
					tt.equals(Character.valueOf('*')) || 
					tt.equals(Character.valueOf('+'))) {
				op2 = dstack.pop();
				op1 = dstack.pop();
				if(tt.equals(Character.valueOf('/'))) {
					opr = op1 / op2;
					dstack.push(opr);
				}
				else if(tt.equals(Character.valueOf('-'))) {
					opr = op1 - op2;
					dstack.push(opr);
				}
				else if(tt.equals(Character.valueOf('*'))) {
					opr = op1 * op2;
					dstack.push(opr);
				}
				else if(tt.equals(Character.valueOf('+'))) {
					opr = op1 + op2;
					dstack.push(opr);
				}
			}
			else {
				dstack.push((Double) tt);
			}
		}
		return dstack.pop();
	}
}

class SuperNucleanAristoLastOper implements ActionListener {
	JLabel label;
	JTextField text;
	public SuperNucleanAristoLastOper(JLabel label, JTextField text) {
		this.label = label;
		this.text = text;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String lstr = label.getText();
		String tstr = text.getText();
		if(tstr.isEmpty() && lstr.isEmpty())
			return;
		if(tstr.isEmpty() != true) {
			if(tstr.charAt(0) == '-') {
				String lpair = "(";
				String rpair = ")";
				tstr = tstr.concat(rpair);
				tstr = lpair.concat(tstr);
			}
		}
		lstr = lstr.concat(tstr);
		if(lstr.charAt(lstr.length() - 1) == '*' || 
				lstr.charAt(lstr.length() - 1) == '-' ||
				lstr.charAt(lstr.length() - 1) == '/' ||
				lstr.charAt(lstr.length() - 1) == '+') {
			int index = lstr.length() - 1;
			char[] charr = lstr.toCharArray();
			charr[index] = '\0';
			char[] strc = new char[index];
			for(int i = 0;i < index;i++) {
				strc[i] = charr[i];
			}
			lstr = String.valueOf(strc);
		}
		SuperStack stk = new SuperStack(lstr);
		try {
			stk.valueOfOperator();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			label.setText("valueOfOperatorException");
			text.setText("Error");
			return;
		}
		
		double result = stk.resultation();
		if(Math.abs((result - (int)result)) > 0) {
			lstr = String.valueOf(result);
			text.setText(lstr);
			label.setText("");
		}
		else {
			int lasbi = (int)result;
			lstr = String.valueOf(lasbi);
			text.setText(lstr);
			label.setText("");
		}
	}
}


class ButtonNum implements ActionListener {
	JTextField text;
	JButton button;
	public ButtonNum(JButton button, JTextField text) {
		this.text = text;
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String inject = button.getText();
		if(inject.equals(".")) {             //button is "."?
			if(text.getText().contains(".")) //text is Double?
				return;
			else if(text.getText().isEmpty())
				text.setText("0.");
			else
				text.setText(text.getText().concat(inject));
		}
		else
			text.setText(text.getText().concat(inject));
	}
}

class SqrAction implements ActionListener {
	JTextField text;
	JButton button;
	int numi;
	double numd;
	public SqrAction(JButton button, JTextField text) {
		this.text = text;
		this.button = button;
		numi = 0;
		numd = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e)  {
		// TODO Auto-generated method stub
		String inject = text.getText();
		if(inject.isEmpty() || inject.equals("Infinity") || inject.equals("NaN"))
			return;
		else {
			try {
				if(inject.contains(".")) {
					numd = Double.parseDouble(inject);
					numd = numd * numd;
					inject = String.valueOf(numd);
					text.setText(inject);
				}
				else {
					numi = Integer.parseInt(inject);
					numi = numi * numi;
					inject = String.valueOf(numi);
					text.setText(inject);
				}
			}
			catch(Exception er) {
				text.setText("ERROR SCRIPT");
				return;
			}
		}
	}
}

class EraseAction implements ActionListener {
	JTextField text;
	public EraseAction(JTextField text) {
		this.text = text;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String inject = text.getText();
		if(inject.isEmpty())      //text is empty?
			return;
		int index = inject.length() - 1;
		char[] str = inject.toCharArray();
		char[] strc = new char[index];
		for(int i = 0;i < index;i++) {
			strc[i] = str[i];
		}
		inject = String.valueOf(strc);
		text.setText(inject);
	}
}


class ClearAction implements ActionListener {
	JTextField text;
	JLabel label;
	public ClearAction(JTextField text, JLabel label) {
		this.text = text;
		this.label = label;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		text.setText("");
		label.setText("");
	}
}

class PlmaAction implements ActionListener {
	JTextField text;
	public PlmaAction(JTextField text) {
		this.text = text;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String minus = new String("-");
		String inject = text.getText();
		if(inject.isEmpty())
			return;
		if(inject.contains("-"))
			inject = inject.substring(1);
		else
			inject = minus.concat(inject);
		text.setText(inject);
	}
}

class OperatorAction implements ActionListener {
	JLabel label;
	JTextField text;
	JButton button;
	public OperatorAction(JLabel label, JTextField text, JButton button) {
		this.label = label;
		this.text = text;
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String str = text.getText();
		String bstr = button.getText();
		String lstr = label.getText();
		if(lstr.isEmpty()) {
			if(str.isEmpty()) {
				lstr = String.valueOf(0);
				lstr = lstr.concat(bstr);
			}
			else {
				if(str.contains("-")) {
					String lpair = "(";
					lpair = lpair.concat(str);
					lpair = lpair.concat(")");
					lstr = lstr.concat(lpair);
					lstr = lstr.concat(bstr);
				}
				else {
					lstr = lstr.concat(str);
					lstr = lstr.concat(bstr);
				}
			}
		}
		else {
			if((lstr.charAt(lstr.length() - 1) == '/' || 
					lstr.charAt(lstr.length() - 1) == '*' ||
					lstr.charAt(lstr.length() - 1) == '+' || 
					lstr.charAt(lstr.length() - 1) == '-') && str.isEmpty())
				return;
			if(str.contains("-")) {
				String lpair = "(";
				lpair = lpair.concat(str);
				lpair = lpair.concat(")");
				lstr = lstr.concat(lpair);
				lstr = lstr.concat(bstr);
			}
			else {
				lstr = lstr.concat(str);
				lstr = lstr.concat(bstr);
			}
		}
		label.setText(lstr);
		text.setText("");
	}
	
}


class Sino implements ActionListener {
	JTextField text;
	double d;
	Sino(JTextField text) {
		this.text = text;
		this.d = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ae = text.getText();
		if(ae.isEmpty())    //text is empty?    
			return;
		try {
			text.setText(String.valueOf(Math.sin(Math.toRadians(Double.valueOf(ae)))));
		}
		catch(Exception er) {
			text.setText("CRITICAL ERROR");
			return;
		}
	}
}


class Cosi implements ActionListener {
	JTextField text;
	double d;
	Cosi(JTextField text) {
		this.text = text;
		this.d = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ae = text.getText();
		if(ae.isEmpty())    //text is empty?
			return;
		try {
			text.setText(String.valueOf(Math.cos(Math.toRadians(Double.valueOf(ae)))));
		}
		catch(Exception er) {
			text.setText("CRITICAL ERROR");
			return;
		}
	}
}


class Tanu implements ActionListener {
	JTextField text;
	double d;
	Tanu(JTextField text) {
		this.text = text;
		this.d = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ae = text.getText();
		if(ae.isEmpty())    //text is empty?
			return;
		try {
			text.setText(String.valueOf(Math.tan(Math.toRadians(Double.valueOf(ae)))));
		}
		catch(Exception er) {
			text.setText("CRITICAL ERROR");
			return;
		}
	}
}

class Jegopgeun implements ActionListener {
	JTextField text;
	double d;
	Jegopgeun(JTextField text) {
		this.text = text;
		d = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(text.getText().isEmpty())
			return;
		try {
			d = Double.valueOf(text.getText());
		}
		catch(Exception er) {
			text.setText("CRITICAL ERROR");
			return;
		}
		d = Math.sqrt(d);
		text.setText(String.valueOf(d));
	}
}

class Oksustation implements ActionListener {
	JTextField text;
	double d;
	public Oksustation(JTextField text) {
		// TODO Auto-generated constructor stub
		this.text = text;
		this.d = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(text.getText().isEmpty())
			return;
		try {
			d = 1 / Double.valueOf(text.getText());
		}
		catch(Exception er) {
			text.setText("Oksustation_Destroy");
			return;
		}
		text.setText(String.valueOf(d));
	}
}

class MemoryStack implements ActionListener {
	static Stack<Object> stacke;
	JTextField text;
	JButton button;
	JLabel label;
	double d;
	int i;
	public MemoryStack(JTextField text, JButton button, JLabel label) {
		this.text = text;
		this.button = button;
		this.label = label;
		stacke = new Stack<Object>();
		d = 0;
		i = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String axe = text.getText();
		if(button.getText().equals("mpush")) {
			try {
				if(axe.contains(".")) {
					d = Double.valueOf(axe);
					stacke.push(d);
					label.setText("save");
				}
				else {
					i = Integer.valueOf(axe);
					stacke.push(i);
					label.setText("save");
				}
			}
			catch(Exception er) {
				label.setText("fail");
			}
		}
		else if(button.getText().equals("mpop")) {
			try {
				Object opr = stacke.pop();
				text.setText(String.valueOf(opr));
				label.setText("load");
			}
			catch(Exception er2) {
				label.setText("fail");
			}
		}
	}
}


class AntiChristian implements ActionListener {
	JTextField text;
	double d;
	int i;
	public AntiChristian(JTextField text) {
		// TODO Auto-generated constructor stub
		this.text = text;
		this.d = 0;
		this.i = 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ope = text.getText();
		try {
			text.setText(String.valueOf(Math.log10(Double.valueOf(ope))));
		}
		catch(Exception er) {
			text.setText("Log : FAIL");
		}
	}
}


public class Calculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mainframe = new JFrame("Calculator v2.3");
		JLabel historyLabel = new JLabel();
		JTextField currentLabel = new JTextField();
		currentLabel.setEditable(false);
		currentLabel.setBackground(Color.WHITE);
		currentLabel.setHorizontalAlignment(JTextField.RIGHT);
		historyLabel.setHorizontalAlignment(JLabel.RIGHT);
		currentLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 27));
		mainframe.setLocation(500, 100);
		mainframe.setPreferredSize(new Dimension(350, 585));
		Container contentpane = mainframe.getContentPane();
		JPanel buttonPanel = new JPanel();
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.setLayout(new GridLayout(8, 4));
		displayPanel.add(historyLabel);
		displayPanel.add(currentLabel);
		/////Standard Setting Window/////
		
		/////Button Adding/////
		/////Number = LIGHT_GRAY
		/////Number Assist = CYAN
		/////Four Operator = MAGENTA
		/////Mathematica = GRAY
		/////Memory Stacke = PINK
		JButton plma = new JButton("+/-");
		plma.setBackground(Color.CYAN);
		JButton point = new JButton(".");
		point.setBackground(Color.LIGHT_GRAY);
		JButton zero = new JButton("0");
		zero.setBackground(Color.LIGHT_GRAY);
		JButton one = new JButton("1");
		one.setBackground(Color.LIGHT_GRAY);
		JButton two = new JButton("2");
		two.setBackground(Color.LIGHT_GRAY);
		JButton three = new JButton("3");
		three.setBackground(Color.LIGHT_GRAY);
		JButton four = new JButton("4");
		four.setBackground(Color.LIGHT_GRAY);
		JButton five = new JButton("5");
		five.setBackground(Color.LIGHT_GRAY);
		JButton six = new JButton("6");
		six.setBackground(Color.LIGHT_GRAY);
		JButton seven = new JButton("7");
		seven.setBackground(Color.LIGHT_GRAY);
		JButton eight = new JButton("8");
		eight.setBackground(Color.LIGHT_GRAY);
		JButton nine = new JButton("9");
		nine.setBackground(Color.LIGHT_GRAY);
		JButton equal = new JButton("=");
		equal.setBackground(Color.MAGENTA);
		JButton plus = new JButton("+");
		plus.setBackground(Color.MAGENTA);
		JButton minus = new JButton("-");
		minus.setBackground(Color.MAGENTA);
		JButton multi = new JButton("*");
		multi.setBackground(Color.MAGENTA);
		JButton div = new JButton("/");
		div.setBackground(Color.MAGENTA);
		JButton clear = new JButton("C");
		JButton erase = new JButton("<x>");
		JButton sqr = new JButton("^2");
		sqr.setBackground(Color.GRAY);
		
		//Extension Set
		JButton sinb = new JButton("sin");
		sinb.setBackground(Color.GRAY);
		JButton cosb = new JButton("cos");
		cosb.setBackground(Color.GRAY);
		JButton tanb = new JButton("tan");
		tanb.setBackground(Color.GRAY);
		JButton jgg = new JButton("v-");
		jgg.setBackground(Color.GRAY);
		JButton oksu = new JButton("1/x");
		oksu.setBackground(Color.GRAY);
		JButton mpush = new JButton("mpush");
		mpush.setBackground(Color.PINK);
		JButton mpop = new JButton("mpop");
		mpop.setBackground(Color.PINK);
		JLabel errlbl = new JLabel("Mem", JLabel.CENTER);
		errlbl.setBackground(Color.WHITE);
		JButton parseint = new JButton("parseINT");
		parseint.setBackground(Color.CYAN);
		JButton parsedouble = new JButton("x.1");
		parsedouble.setBackground(Color.CYAN);
		JButton logna = new JButton("log(10)");
		logna.setBackground(Color.GRAY);
		JLabel laphael = new JLabel("21621901", JLabel.CENTER);
		
		buttonPanel.add(clear);
		buttonPanel.add(erase);
		buttonPanel.add(sqr);
		buttonPanel.add(div);
		buttonPanel.add(seven);
		buttonPanel.add(eight);
		buttonPanel.add(nine);
		buttonPanel.add(multi);
		buttonPanel.add(four);
		buttonPanel.add(five);
		buttonPanel.add(six);
		buttonPanel.add(minus);
		buttonPanel.add(one);
		buttonPanel.add(two);
		buttonPanel.add(three);
		buttonPanel.add(plus);
		buttonPanel.add(plma);
		buttonPanel.add(zero);
		buttonPanel.add(point);
		buttonPanel.add(equal);
		
		//Extension Set
		buttonPanel.add(sinb);
		buttonPanel.add(cosb);
		buttonPanel.add(tanb);
		buttonPanel.add(jgg);
		buttonPanel.add(oksu);
		buttonPanel.add(mpush);
		buttonPanel.add(mpop);
		buttonPanel.add(errlbl);
		buttonPanel.add(parseint);
		buttonPanel.add(parsedouble);
		buttonPanel.add(logna);
		buttonPanel.add(laphael);
		//////////
		Thread hakbeon = new TToing(laphael);
		hakbeon.start();
		
		GridLayout gridlayout = new GridLayout(2, 1);
		contentpane.setLayout(gridlayout);
		contentpane.add(displayPanel);
		contentpane.add(buttonPanel);
////////Setting Window Display Panel////////
		
		ActionListener buttonNumListner1 = new ButtonNum(one, currentLabel);
		one.addActionListener(buttonNumListner1);
		ActionListener buttonNumListner2 = new ButtonNum(two, currentLabel);
		two.addActionListener(buttonNumListner2);
		ActionListener buttonNumListner3 = new ButtonNum(three, currentLabel);
		three.addActionListener(buttonNumListner3);
		ActionListener buttonNumListner4 = new ButtonNum(four, currentLabel);
		four.addActionListener(buttonNumListner4);
		ActionListener buttonNumListner5 = new ButtonNum(five, currentLabel);
		five.addActionListener(buttonNumListner5);
		ActionListener buttonNumListner6 = new ButtonNum(six, currentLabel);
		six.addActionListener(buttonNumListner6);
		ActionListener buttonNumListner7 = new ButtonNum(seven, currentLabel);
		seven.addActionListener(buttonNumListner7);
		ActionListener buttonNumListner8 = new ButtonNum(eight, currentLabel);
		eight.addActionListener(buttonNumListner8);
		ActionListener buttonNumListner9 = new ButtonNum(nine, currentLabel);
		nine.addActionListener(buttonNumListner9);
		ActionListener buttonNumListner0 = new ButtonNum(zero, currentLabel);
		zero.addActionListener(buttonNumListner0);
		ActionListener buttonNumListnerp = new ButtonNum(point, currentLabel);
		point.addActionListener(buttonNumListnerp);
		
		ActionListener sqrbutton = new SqrAction(sqr, currentLabel);
		sqr.addActionListener(sqrbutton);
		
		ActionListener clearbutton = new ClearAction(currentLabel, historyLabel);
		clear.addActionListener(clearbutton);
		
		ActionListener erasebutton = new EraseAction(currentLabel);
		erase.addActionListener(erasebutton);
		
		ActionListener plmabutton = new PlmaAction(currentLabel);
		plma.addActionListener(plmabutton);
		
		ActionListener plusbutton = new OperatorAction(historyLabel, currentLabel, plus);
		plus.addActionListener(plusbutton);
		
		ActionListener minusbutton = new OperatorAction(historyLabel, currentLabel, minus);
		minus.addActionListener(minusbutton);
		
		ActionListener mulbutton = new OperatorAction(historyLabel, currentLabel, multi);
		multi.addActionListener(mulbutton);
		
		ActionListener divbutton = new OperatorAction(historyLabel, currentLabel, div);
		div.addActionListener(divbutton);
		
		//@KKYuingTestmsq
		ActionListener superbutton = new SuperNucleanAristoLastOper(historyLabel, currentLabel);
		equal.addActionListener(superbutton);
		
		//Extension Set
		ActionListener sinbutton = new Sino(currentLabel);
		sinb.addActionListener(sinbutton);
		
		ActionListener cosbutton = new Cosi(currentLabel);
		cosb.addActionListener(cosbutton);
		
		ActionListener tanbutton = new Tanu(currentLabel);
		tanb.addActionListener(tanbutton);
		
		ActionListener jggbutton = new Jegopgeun(currentLabel);
		jgg.addActionListener(jggbutton);
		
		ActionListener oksubutton = new Oksustation(currentLabel);
		oksu.addActionListener(oksubutton);
		
		ActionListener memys = new MemoryStack(currentLabel, mpush, errlbl);
		ActionListener memyl = new MemoryStack(currentLabel, mpop, errlbl);
		mpush.addActionListener(memys);
		mpop.addActionListener(memyl);
		
		ActionListener logmachine = new AntiChristian(currentLabel);
		logna.addActionListener(logmachine);
		
		ActionListener intparser = new Parser(parseint, currentLabel);
		parseint.addActionListener(intparser);
		
		ActionListener doubleparser = new Parser(parsedouble, currentLabel);
		parsedouble.addActionListener(doubleparser);
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.pack();
		mainframe.setVisible(true);	
	}
}
