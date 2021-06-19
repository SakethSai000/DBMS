package DBMS;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//1602-19-737-097 P Sai Saketh
@SuppressWarnings("serial")
public class InsertTables extends Frame implements ActionListener 
{
	MenuBar mb;
	MenuItem m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20,m21,m22,m23,m24;
	Menu menu,Customer,orders,sales,employee,payment;
	TextField item_IDText, item_nameText, costText, available_quantityText ;
	TextField customer_IDText, customer_nameText, customer_PHNOText, tokenText, order_id1Text;
	TextField order_idText, item_idText, ordered_quantityText ;
	TextField item_ID1Text, quantity_soldText, total_amountText;
	TextField employee_IDText,employee_nameText,employee_PHNOText;
	TextField customer_ID1Text,order_id2Text,pay_modeText,pay_IDText,totalText;
	Choice menuChoice,ordersChoice,CustomerChoice;
	TextArea errorText;
	Connection connection;
	Statement statement;
	
	
	//For updates
	Button updateButton;
	Button insertButton;
	List menuList,ordersList,CustomerList,salesList,employeeList,payList;
	ResultSet rs;
	//TextField item_IDText, item_nameText, costText, available_quantityText ;
	
	//For delete
	Button deleteRowButton;
	
	public InsertTables()
	{
		try 
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) 
		{
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB ();
	}

	public void connectToDB() 
    {
		try 
		{
		  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","saketh","vasavi");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
    }
	public void buildFrame()
	{
		//Basic Frame Properties
		setTitle("Bakery Management System");
		setSize(500, 600);
		setVisible(true);
		
		//menubar
		mb = new MenuBar();
		setMenuBar(mb);  
        setSize(550,500);  
        setLayout(null);  
        setVisible(true);
        
        //Menu 
         menu=new Menu("Menu");  
        
         m1=new MenuItem("Insert Menu");  
         m2=new MenuItem("Update Menu");  
         m3=new MenuItem("Delete Menu");  
         m4=new MenuItem("View Menu");
           
        menu.add(m1);  
        menu.add(m2);  
        menu.add(m3); 
        menu.add(m4);
        
       // mb.add(menu);
        
        //Customer
        Customer=new Menu("Customer"); 
        m5=new MenuItem("Insert Customer");
        m6=new MenuItem("Update Customer");
        m7=new MenuItem("Delete Customer");
        m8=new MenuItem("View Customer");
        
        Customer.add(m5);
        Customer.add(m6);
        Customer.add(m7);
        Customer.add(m8);
        
        
        
        //Orders
        orders=new Menu("Orders");  
        m9=new MenuItem("Insert Orders");
        m10=new MenuItem("Update Orders");
        m11=new MenuItem("Delete Orders");
        m12=new MenuItem("View Orders");
       
        orders.add(m9);
        orders.add(m10);
        orders.add(m11);
        orders.add(m12);
        
       // mb.add(orders);
        //mb.add(Customer);
 
        
        //sales
        sales=new Menu("Sales");
        m13=new MenuItem("Insert Sales");
        m14=new MenuItem("Update Sales");
        m15=new MenuItem("Delete Sales");
        m16=new MenuItem("View Sales");
        
        sales.add(m13);
        sales.add(m14);
        sales.add(m15);
        sales.add(m16);
        
       // mb.add(sales);
        
        employee=new Menu("Employee");
        m17=new MenuItem("Insert Employee");
        m18=new MenuItem("Update Employee");
        m19=new MenuItem("Delete Employee");
        m20=new MenuItem("View Employee");
        
        employee.add(m17);
        employee.add(m18);
        employee.add(m19);
        employee.add(m20);
        
       // mb.add(employee);
        
        payment=new Menu("Payment");
        m21=new MenuItem("Insert Payment");
        m22=new MenuItem("Update Payment");
        m23=new MenuItem("Delete Payment");
        m24=new MenuItem("View Payment");
        
        payment.add(m21);
        payment.add(m22);
        payment.add(m23);
        payment.add(m24);
        
       // mb.add(payment);
        mb.add(menu);
        mb.add(orders);
        mb.add(Customer);
        mb.add(payment);
        mb.add(sales);
        mb.add(employee);
        
       //Registering action Listeners
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        m8.addActionListener(this);
        m9.addActionListener(this);
        m10.addActionListener(this);
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m14.addActionListener(this);
        m15.addActionListener(this);
        m16.addActionListener(this);
        m17.addActionListener(this);
        m18.addActionListener(this);
        m19.addActionListener(this);
        m20.addActionListener(this);
        m21.addActionListener(this);
        m22.addActionListener(this);
        m23.addActionListener(this);
        m24.addActionListener(this);
       // setBackground(Color.blue);
        repaint();
        
	}
	public void actionPerformed(ActionEvent ae)
	{
		String arg = ae.getActionCommand();
		if(arg.equals("Insert Menu"))
			this.buildGUIMenu();
		if(arg.equals("Update Menu"))
			this.updateMenuGUI();
		if(arg.equals("Delete Menu"))
			this.deleteGUIMenu();
		if(arg.equals("View Menu"))
			this.viewMenuGUI();	
		if(arg.equals("Insert Customer"))
			this.buildGUICustomer();
		if(arg.equals("Update Customer"))
			this.updateCustomerGUI();
		if(arg.equals("Delete Customer"))
			this.deleteGUICustomer();
		if(arg.equals("View Customer"))
			this.viewCustomerGUI();	
		if(arg.equals("Insert Orders"))
			this.buildGUIOrders();
		if(arg.equals("Update Orders"))
			this.updateOrdersGUI();
		if(arg.equals("Delete Orders"))
			this.deleteGUIOrders();
		if(arg.equals("View Orders"))
			this.viewOrdersGUI();	
		if(arg.equals("Insert Sales"))
			this.buildGUISales();
		if(arg.equals("Update Sales"))
			this.updateGUISales();
		if(arg.equals("Delete Sales"))
			this.deleteGUISales();
		if(arg.equals("View Sales"))
			this.viewSalesGUI();
		if(arg.equals("Insert Employee"))
			this.buildGUIEmployee();
		if(arg.equals("Update Employee"))
			this.updateGUIEmployee();
		if(arg.equals("Delete Employee"))
			this.deleteGUIEmployee();
		if(arg.equals("View Employee"))
			this.viewEmployeeGUI();
		if(arg.equals("Insert Payment"))
			this.buildGUIPayment();
		if(arg.equals("Update Payment"))
			this.updatePaymentGUI();
		if(arg.equals("Delete Payment"))
			this.deleteGUIPayment();
		if(arg.equals("View Payment"))
			this.viewPaymentGUI();
	}
	
	private void loadOrders1() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM order_list");
		  while (rs.next()) 
		  {
			  ordersChoice.add(rs.getString("order_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	
	public void buildGUIMenu() 
	{	
		removeAll();
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO items_list VALUES('" + item_IDText.getText() + "', " + "'" + item_nameText.getText() + "'," + costText.getText() + ",'" + available_quantityText .getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		item_IDText = new TextField(15);
		item_nameText = new TextField(15);
		costText = new TextField(15);
		available_quantityText = new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Item ID:"));
		first.add(item_IDText);
		first.add(new Label("Item Name:"));
		first.add(item_nameText);
		first.add(new Label("Item cost:"));
		first.add(costText);
		first.add(new Label("Available quantity:"));
		first.add(available_quantityText );
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
		//setBackground(Color.white);
	    
	}
	public void buildGUICustomer() 
	{	 	
		removeAll();
		ordersChoice = new Choice();
		loadOrders1();
		add(ordersChoice);
		
		//When a list item is selected populate the text fields
		ordersChoice.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM order_list");
					while (rs.next()) 
					{
						if (rs.getString("order_id").equals(ordersChoice.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						order_id1Text.setText(rs.getString("order_id"));
						//item_idText.setText(rs.getString("item_id"));
						//ordered_quantityText .setText(rs.getString("ordered_quantity"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	   
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO customer VALUES('" + customer_IDText .getText() + "', '" + customer_nameText .getText() + "','" + customer_PHNOText .getText() + "','" + tokenText.getText()+ "','"+ order_id1Text.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		customer_IDText  = new TextField(15);
		customer_nameText  = new TextField(15);
		customer_PHNOText  = new TextField(15);
		tokenText= new TextField(15);
		order_id1Text= new TextField(15);
		order_id1Text.setEditable(false);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_IDText );
		first.add(new Label("Customer Name:"));
		first.add(customer_nameText );
		first.add(new Label("Customer PHNO:"));
		first.add(customer_PHNOText );
		first.add(new Label("Token:"));
		first.add(tokenText);
		first.add(new Label("Order ID:"));
		first.add(order_id1Text);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    
	
	}
	
	private void loadCustomer1() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM customer");
		  while (rs.next()) 
		  {
			  CustomerChoice.add(rs.getString("customer_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	
	public void buildGUIPayment() 
	{	 	
		//customer_ID1Text,order_id2Text,totalText,pay_modeText,pay_IDText
		 removeAll();
		 CustomerChoice = new Choice();
		 loadCustomer1();
		 add(CustomerChoice);
			
			//When a list item is selected populate the text fields
			CustomerChoice.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM customer");
						while (rs.next()) 
						{
							if (rs.getString("customer_id").equals(CustomerChoice.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							customer_ID1Text .setText(rs.getString("customer_id"));
							//customer_nameText .setText(rs.getString("customer_name"));
							//customer_PHNOText .setText(rs.getString("customer_PHNO"));
							//tokenText.setText(rs.getString("token"));
							order_id2Text.setText(rs.getString("order_id"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});	
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO payment VALUES('" + customer_ID1Text .getText() + "', '" + order_id2Text.getText() + "','" + pay_modeText.getText() + "','" + pay_IDText.getText()+ "','"+ totalText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		customer_ID1Text  = new TextField(15);
		customer_ID1Text.setEditable(false);
		order_id2Text  = new TextField(15);
		order_id2Text.setEditable(false);
		pay_modeText= new TextField(15);
		pay_IDText= new TextField(15);
		totalText  = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_ID1Text );
		first.add(new Label("Order ID:"));
		first.add(order_id2Text);
		first.add(new Label("Pay Mode:"));
		first.add(pay_modeText);
		first.add(new Label("Pay ID:"));
		first.add(pay_IDText);
		first.add(new Label("Total:"));
		first.add(totalText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    
	
	}
	public void buildGUISales() 
	{	removeAll();	
		menuChoice = new Choice();
		loadMenu1();
		add(menuChoice);
	
		//When a list item is selected populate the text fields
		menuChoice.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM items_list");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(menuChoice.getSelectedItem()))
							break;
					}
					if (!rs.isAfterLast()) 
					{
						item_ID1Text.setText(rs.getString("item_id"));
						//item_nameText.setText(rs.getString("item_name"));
						//costText.setText(rs.getString("cost"));
						//available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	 
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO sales VALUES('" + item_ID1Text.getText() + "', " + "'" + quantity_soldText.getText() + "'," + "'"+total_amountText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		item_ID1Text = new TextField(15);
		item_ID1Text.setEditable(false);
		quantity_soldText = new TextField(15);
		total_amountText  = new TextField(15);
		//ordertypeText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Item ID:"));
		first.add(item_ID1Text);
		first.add(new Label("Quantity Sold:"));
		first.add(quantity_soldText);
		first.add(new Label("Total Amount:"));
		first.add(total_amountText);
		//first.add(new Label("Payment Type:"));
		//first.add(ordertypeText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);
		

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    

	}
	
	public void buildGUIEmployee() 
	{	
		//employee_IDText,employee_nameText,employee_PHNO
		removeAll();	
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO employee_list VALUES('" + employee_IDText.getText() + "', " + "'" + employee_nameText.getText() + "'," + "'"+employee_PHNOText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		employee_IDText = new TextField(15);
		employee_nameText = new TextField(15);
		employee_PHNOText  = new TextField(15);
		//ordertypeText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Employee ID:"));
		first.add(employee_IDText);
		first.add(new Label("Employee Name:"));
		first.add(employee_nameText);
		first.add(new Label("Employee PHNO:"));
		first.add(employee_PHNOText);
		//first.add(new Label("Payment Type:"));
		//first.add(ordertypeText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);
		

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    

	}
	
	private void loadMenu1() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM items_list");
		  while (rs.next()) 
		  {
			  menuChoice.add(rs.getString("item_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	
	public void buildGUIOrders() 
	{	removeAll();	
	    menuChoice=new Choice();
		loadMenu1();
		add(menuChoice);
		menuChoice.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM items_list");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(menuChoice.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_idText.setText(rs.getString("item_id"));
						//item_nameText.setText(rs.getString("item_name"));
						//costText.setText(rs.getString("cost"));
						//available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO order_list VALUES('" + order_idText .getText() + "', " + "'" + item_idText.getText() + "'," + "'"+ordered_quantityText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		order_idText = new TextField(15);
		item_idText = new TextField(15);
		item_idText.setEditable(false);
		ordered_quantityText  = new TextField(15);
		//ordertypeText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Order ID:"));
		first.add(order_idText);
		first.add(new Label("Item ID:"));
		first.add(item_idText);
		first.add(new Label("Ordered Quantity:"));
		first.add(ordered_quantityText );
		//first.add(new Label("Payment Type:"));
		//first.add(ordertypeText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);
		

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    

	}
	
	private void loadEmployee() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM employee_list");
		  while (rs.next()) 
		  {
			  employeeList.add(rs.getString("employee_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateGUIEmployee() 
	{	
		//employee_IDText,employee_nameText,employee_PHNO;
		removeAll();
		employeeList = new List(6);
		loadEmployee();
		add(employeeList);
		
		//When a list item is selected populate the text fields
		employeeList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM employee_list");
					while (rs.next()) 
					{
						if (rs.getString("employee_id").equals(employeeList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						employee_IDText.setText(rs.getString("employee_id"));
						employee_nameText.setText(rs.getString("employee_name"));
						employee_PHNOText.setText(rs.getString("employee_PHNO"));
						//available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE employee_list "
					+ "SET employee_PHNO=" + employee_PHNOText.getText()
					+ " WHERE employee_id = '" + employeeList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					employeeList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		//employee_IDText,employee_nameText,employee_PHNOText;
		
		employee_IDText = new TextField(15);
		employee_IDText.setEditable(false);
		employee_nameText = new TextField(15);
		employee_nameText.setEditable(false);
		employee_PHNOText = new TextField(15);
		//available_quantityText  = new TextField(15);
		//available_quantityText .setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Employee ID:"));
		first.add(employee_IDText);
		first.add(new Label("Employee Name:"));
		first.add(employee_nameText);
		first.add(new Label("Employee PHNO:"));
		first.add(employee_PHNOText);
		//first.add(new Label("Quantity available:"));
		//first.add(available_quantityText );
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	private void loadSales() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM sales");
		  while (rs.next()) 
		  {
			  salesList.add(rs.getString("item_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateGUISales() 
	{	
		//item_ID1Text, quantity_soldText, total_amountText;
		removeAll();
		salesList = new List(6);
		loadSales();
		add(salesList);
		
		//When a list item is selected populate the text fields
		salesList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM sales");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(salesList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_ID1Text.setText(rs.getString("item_id"));
						quantity_soldText.setText(rs.getString("quantity_sold"));
						total_amountText.setText(rs.getString("total_amount"));
						//available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE sales "
					+ "SET quantity_sold=" + quantity_soldText.getText()+
					","+"total_amount="+ total_amountText.getText()
					+ " WHERE item_id = '" + salesList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					salesList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		item_ID1Text = new TextField(15);
		item_ID1Text.setEditable(false);
		quantity_soldText = new TextField(15);
		//item_nameText.setEditable(false);
		total_amountText = new TextField(15);
		//available_quantityText  = new TextField(15);
		//available_quantityText .setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Item ID:"));
		first.add(item_ID1Text);
		first.add(new Label("Quantity Sold:"));
		first.add(quantity_soldText);
		first.add(new Label("Total Amount:"));
		first.add(total_amountText);
		//first.add(new Label("Quantity available:"));
		//first.add(available_quantityText );
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}

	
	
	private void loadMenu() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM items_list");
		  while (rs.next()) 
		  {
			  menuList.add(rs.getString("item_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateMenuGUI() 
	{	
		removeAll();
		menuList = new List(6);
		loadMenu();
		add(menuList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM items_list");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(menuList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_IDText.setText(rs.getString("item_id"));
						item_nameText.setText(rs.getString("item_name"));
						costText.setText(rs.getString("cost"));
						available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE items_list "
					+ "SET cost=" + costText.getText()  
					+ " WHERE item_id = '" + menuList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					menuList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		item_IDText = new TextField(15);
		item_IDText.setEditable(false);
		item_nameText = new TextField(15);
		item_nameText.setEditable(false);
		costText = new TextField(15);
		available_quantityText  = new TextField(15);
		available_quantityText .setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Item ID:"));
		first.add(item_IDText);
		first.add(new Label("Item Name:"));
		first.add(item_nameText);
		first.add(new Label("Item cost:"));
		first.add(costText);
		first.add(new Label("Quantity available:"));
		first.add(available_quantityText );
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	
	public void deleteGUIMenu() 
	{	
		removeAll();
	    menuList = new List(10);
		loadMenu();
		add(menuList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM items_list");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(menuList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_IDText.setText(rs.getString("item_id"));
						item_nameText.setText(rs.getString("item_name"));
						costText.setText(rs.getString("cost"));
						available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete menu Button
		deleteRowButton = new Button("Delete");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM items_list WHERE item_id = '" + menuList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					item_IDText.setText(null);
					item_nameText.setText(null);
					costText.setText(null);
					available_quantityText .setText(null);
					menuList.removeAll();
					loadMenu();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		item_IDText = new TextField(15);
		item_nameText = new TextField(15);
		costText = new TextField(15);
		available_quantityText  = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		item_IDText.setEditable(false);
		item_nameText.setEditable(false);
		costText.setEditable(false);
		available_quantityText .setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Item ID:"));
		first.add(item_IDText);
		first.add(new Label("Item Name:"));
		first.add(item_nameText);
		first.add(new Label("Item cost:"));
		first.add(costText);
		first.add(new Label("Quantity available:"));
		first.add(available_quantityText );
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	private void loadOrders() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM order_list");
		  while (rs.next()) 
		  {
			  ordersList.add(rs.getString("order_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateOrdersGUI() 
	{	
		removeAll();
		ordersList = new List(6);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM order_list");
					while (rs.next()) 
					{
						if (rs.getString("order_id").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						order_idText.setText(rs.getString("order_id"));
						item_idText.setText(rs.getString("item_id"));
						ordered_quantityText .setText(rs.getString("ordered_quantity"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE order_list "
					+ "SET ordered_quantity=" + ordered_quantityText.getText()  
					+ " WHERE order_id = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		order_idText = new TextField(15);
		order_idText.setEditable(false);
		item_idText = new TextField(15);
		item_idText.setEditable(false);
		ordered_quantityText  = new TextField(15);
		//ordertypeText = new TextField(15);
		//ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Order ID:"));
		first.add(order_idText);
		first.add(new Label("Item ID:"));
		first.add(item_idText);
		first.add(new Label("Ordered Quantity:"));
		first.add(ordered_quantityText );
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}

	public void deleteGUIOrders()
	{
		removeAll();
	    ordersList = new List(10);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM order_list");
					while (rs.next()) 
					{
						if (rs.getString("order_id").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						order_idText.setText(rs.getString("order_id"));
						item_idText.setText(rs.getString("item_id"));
						ordered_quantityText .setText(rs.getString("ordered_quantity"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete orders Button
		deleteRowButton = new Button("Delete");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM order_list WHERE order_id = '" + ordersList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					order_idText.setText(null);
					item_idText.setText(null);
					ordered_quantityText .setText(null);
					//ordertypeText.setText(null);
					ordersList.removeAll();
					loadOrders();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		order_idText = new TextField(15);
		item_idText = new TextField(15);
		ordered_quantityText  = new TextField(15);
		//ordertypeText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		order_idText.setEditable(false);
		item_idText.setEditable(false);
		ordered_quantityText .setEditable(false);
		//ordertypeText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Order ID:"));
		first.add(order_idText);
		first.add(new Label("Item ID:"));
		first.add(item_idText);
		first.add(new Label("Ordered Quantity:"));
		first.add(ordered_quantityText );
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUIEmployee()
	{
		//employee_IDText,employee_nameText,employee_PHNOText
		removeAll();
	    employeeList = new List(10);
		loadEmployee();
		add(employeeList);
		
		//When a list item is selected populate the text fields
		employeeList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM employee_list");
					while (rs.next()) 
					{
						if (rs.getString("employee_id").equals(employeeList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						employee_IDText.setText(rs.getString("employee_id"));
						employee_nameText.setText(rs.getString("employee_name"));
						employee_PHNOText.setText(rs.getString("employee_PHNO"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete orders Button
		deleteRowButton = new Button("Delete");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM employee_list WHERE employee_id = '" + employeeList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					employee_IDText.setText(null);
					employee_nameText.setText(null);
					employee_PHNOText.setText(null);
					//ordertypeText.setText(null);
					employeeList.removeAll();
					loadOrders();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		//employee_IDText,employee_nameText,employee_PHNOText
		
		employee_IDText = new TextField(15);
		employee_nameText = new TextField(15);
		employee_PHNOText  = new TextField(15);
		//ordertypeText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		employee_IDText .setEditable(false);
		employee_nameText.setEditable(false);
		employee_PHNOText .setEditable(false);
		//ordertypeText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Employee ID:"));
		first.add(employee_IDText);
		first.add(new Label("Employee Name:"));
		first.add(employee_nameText);
		first.add(new Label("Employee PHNO:"));
		first.add(employee_PHNOText);
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUISales()
	{
		//item_ID1Text, quantity_soldText, total_amountText;
		removeAll();
	    salesList = new List(10);
		loadSales();
		add(salesList);
		
		//When a list item is selected populate the text fields
		salesList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM sales");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(salesList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_ID1Text.setText(rs.getString("item_id"));
						quantity_soldText.setText(rs.getString("quantity_sold"));
						total_amountText.setText(rs.getString("total_amount"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete orders Button
		deleteRowButton = new Button("Delete");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM sales WHERE item_id = '" + salesList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					item_ID1Text.setText(null);
					quantity_soldText.setText(null);
					 total_amountText.setText(null);
					//ordertypeText.setText(null);
					salesList.removeAll();
					loadSales();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		//item_ID1Text, quantity_soldText, total_amountText;
		 item_ID1Text = new TextField(15);
		 quantity_soldText = new TextField(15);
		 total_amountText  = new TextField(15);
		//ordertypeText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		item_ID1Text.setEditable(false);
		quantity_soldText.setEditable(false);
		total_amountText .setEditable(false);
		//ordertypeText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Item ID:"));
		first.add( item_ID1Text);
		first.add(new Label("Quantity Sold:"));
		first.add(quantity_soldText);
		first.add(new Label("Total Amount:"));
		first.add(total_amountText);
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	private void loadCustomer() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM customer");
		  while (rs.next()) 
		  {
			  CustomerList.add(rs.getString("customer_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateCustomerGUI() 
	{	
		removeAll();
	    CustomerList = new List(10);
		loadCustomer();
		add(CustomerList);
		
		//When a list item is selected populate the text fields
		CustomerList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM customer");
					while (rs.next()) 
					{
						if (rs.getString("customer_id").equals(CustomerList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customer_IDText .setText(rs.getString("customer_id"));
						customer_nameText .setText(rs.getString("customer_name"));
						customer_PHNOText .setText(rs.getString("customer_PHNO"));
						tokenText.setText(rs.getString("token"));
						order_id1Text.setText(rs.getString("order_id"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE customer "
					+ "SET customer_PHNO=" + customer_PHNOText .getText()  
					+ " WHERE customer_id = '" + CustomerList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					CustomerList.removeAll();
					loadCustomer();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		customer_IDText  = new TextField(15);
		customer_IDText .setEditable(false);
		customer_nameText  = new TextField(15);
		customer_nameText .setEditable(false);
		customer_PHNOText  = new TextField(15);
		tokenText = new TextField(15);
		tokenText.setEditable(false);
		order_id1Text=new TextField(15);
		order_id1Text.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_IDText );
		first.add(new Label("Customer Name:"));
		first.add(customer_nameText );
		first.add(new Label("Customer PHNO:"));
		first.add(customer_PHNOText );
		first.add(new Label("Token:"));
		first.add(tokenText);
		first.add(new Label("Order ID:"));
		first.add(order_id1Text);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	private void loadPayment() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM payment");
		  while (rs.next()) 
		  {
			  payList.add(rs.getString("customer_id"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updatePaymentGUI() 
	{	
		//customer_ID1Text,order_id2Text,pay_modeText,pay_IDText,totalText;
		removeAll();
	    payList = new List(10);
		loadPayment();
		add(payList);
		
		//When a list item is selected populate the text fields
		payList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM payment");
					while (rs.next()) 
					{
						if (rs.getString("customer_id").equals(payList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customer_ID1Text .setText(rs.getString("customer_ID"));
						order_id2Text .setText(rs.getString("order_ID"));
						pay_modeText .setText(rs.getString("pay_mode"));
						pay_IDText.setText(rs.getString("pay_ID"));
						totalText.setText(rs.getString("total"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE payment "
					+ "SET pay_mode=" +"'"+pay_modeText .getText()+"'"+
					",pay_ID="+"'"+pay_IDText.getText()+"'"
					+ " WHERE customer_id = '" + payList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					payList.removeAll();
					loadCustomer();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		//customer_ID1Text,order_id2Text,pay_modeText,pay_IDText,totalText;
		
		customer_ID1Text  = new TextField(15);
		customer_ID1Text .setEditable(false);
		order_id2Text  = new TextField(15);
		order_id2Text .setEditable(false);
		pay_modeText = new TextField(15);
		pay_IDText = new TextField(15);
		//pay_IDText.setEditable(false);
		totalText=new TextField(15);
		totalText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_ID1Text );
		first.add(new Label("Order ID:"));
		first.add(order_id2Text);
		first.add(new Label("Pay Mode:"));
		first.add(pay_modeText);
		first.add(new Label("Payment ID:"));
		first.add(pay_IDText);
		first.add(new Label("Total:"));
		first.add(totalText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUICustomer()
	{
		removeAll();
	    CustomerList = new List(10);
		loadCustomer();
		add(CustomerList);
		
		//When a list item is selected populate the text fields
		CustomerList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM customer");
					while (rs.next()) 
					{
						if (rs.getString("customer_id").equals(CustomerList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customer_IDText .setText(rs.getString("customer_id"));
						customer_nameText .setText(rs.getString("customer_name"));
						customer_PHNOText .setText(rs.getString("customer_PHNO"));
						tokenText.setText(rs.getString("token"));
						order_id1Text.setText(rs.getString("order_id"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete Customer Button
		deleteRowButton = new Button("Delete");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM Customer WHERE customer_id = '" + CustomerList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					customer_IDText .setText(null);
					customer_nameText .setText(null);
					customer_PHNOText .setText(null);
					tokenText.setText(null);
					order_id1Text.setText(null);
					CustomerList.removeAll();
					loadCustomer();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		customer_IDText  = new TextField(15);
		customer_nameText  = new TextField(15);
		customer_PHNOText  = new TextField(15);
		tokenText = new TextField(15);
		order_id1Text= new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		customer_IDText .setEditable(false);
		customer_nameText .setEditable(false);
		customer_PHNOText .setEditable(false);
		tokenText.setEditable(false);
		order_id1Text.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_IDText );
		first.add(new Label("Customer Name:"));
		first.add(customer_nameText );
		first.add(new Label("Customer PHNO:"));
		first.add(customer_PHNOText );
		first.add(new Label("Token:"));
		first.add(tokenText);
		first.add(new Label("Order ID:"));
		first.add(order_id1Text);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUIPayment()
	{
		//customer_ID1Text,order_id2Text,pay_modeText,pay_IDText,totalText;
		removeAll();
	    payList = new List(10);
		loadPayment();
		add(payList);
		
		//When a list item is selected populate the text fields
		payList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM payment");
					while (rs.next()) 
					{
						if (rs.getString("customer_id").equals(payList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customer_ID1Text .setText(rs.getString("customer_id"));
						order_id2Text .setText(rs.getString("order_id"));
						pay_modeText.setText(rs.getString("pay_mode"));
						pay_IDText.setText(rs.getString("pay_id"));
						totalText.setText(rs.getString("total"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete Customer Button
		deleteRowButton = new Button("Delete");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM payment WHERE customer_id = '" + payList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					customer_ID1Text .setText(null);
					order_id2Text .setText(null);
					pay_modeText .setText(null);
					pay_IDText.setText(null);
					totalText.setText(null);
					payList.removeAll();
					loadCustomer();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		customer_ID1Text  = new TextField(15);
		order_id2Text  = new TextField(15);
		pay_modeText  = new TextField(15);
		pay_IDText= new TextField(15);
		totalText= new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		customer_ID1Text .setEditable(false);
		order_id2Text .setEditable(false);
		pay_modeText .setEditable(false);
		pay_IDText.setEditable(false);
		totalText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_ID1Text );
		first.add(new Label("Order ID:"));
		first.add(order_id2Text );
		first.add(new Label("Payment Mode:"));
		first.add(pay_modeText);
		first.add(new Label("Payment ID:"));
		first.add(pay_IDText);
		first.add(new Label("Total:"));
		first.add(totalText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}

	public void viewMenuGUI() 
	{	
		removeAll();
		menuList = new List(6);
		loadMenu();
		add(menuList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM items_list");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(menuList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_IDText.setText(rs.getString("item_id"));
						item_nameText.setText(rs.getString("item_name"));
						costText.setText(rs.getString("cost"));
						available_quantityText .setText(rs.getString("available_quantity"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		/*updateButton = new Button("Update Menu");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET dishprice=" + costText.getText()  
					+ " WHERE dishid = '" + menuList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					menuList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});*/
		
		item_IDText = new TextField(15);
		item_IDText.setEditable(false);
		item_nameText = new TextField(15);
		item_nameText.setEditable(false);
		costText = new TextField(15);
		costText.setEditable(false);
		available_quantityText  = new TextField(15);
		available_quantityText .setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Item ID:"));
		first.add(item_IDText);
		first.add(new Label("Item Name:"));
		first.add(item_nameText);
		first.add(new Label("Item cost:"));
		first.add(costText);
		first.add(new Label("Quantity available:"));
		first.add(available_quantityText );
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		//third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewPaymentGUI() 
	{	
		//customer_ID1Text,order_id2Text,pay_modeText,pay_IDText,totalText
		removeAll();
		payList = new List(6);
		loadPayment();
		add(payList);
		
		//When a list item is selected populate the text fields
		payList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM payment");
					while (rs.next()) 
					{
						if (rs.getString("customer_id").equals(payList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customer_ID1Text .setText(rs.getString("customer_id"));
						order_id2Text .setText(rs.getString("order_id"));
						pay_modeText .setText(rs.getString("pay_mode"));
						pay_IDText.setText(rs.getString("pay_id"));
						totalText.setText(rs.getString("total"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		/*//Handle Update Menu Button
		updateButton = new Button("Update Customer");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE Customer "
					+ "SET Customerprice=" + customer_PHNOText .getText()  
					+ " WHERE Customerid = '" + CustomerList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					CustomerList.removeAll();
					loadCustomer();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});*/
		//customer_ID1Text,order_id2Text,pay_modeText,pay_IDText,totalText
		
		customer_ID1Text  = new TextField(15);
		customer_ID1Text .setEditable(false);
		order_id2Text  = new TextField(15);
		order_id2Text .setEditable(false);
		pay_modeText  = new TextField(15);
		pay_modeText .setEditable(false);
		pay_IDText = new TextField(15);
		pay_IDText.setEditable(false);
		totalText=new TextField(15);
		totalText.setEditable(false);
		

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_ID1Text );
		first.add(new Label("Order ID:"));
		first.add(order_id2Text );
		first.add(new Label("Payment Mode:"));
		first.add(pay_modeText );
		first.add(new Label("Payment ID:"));
		first.add(pay_IDText);
		first.add(new Label("Total:"));
		first.add(totalText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		//third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewCustomerGUI() 
	{	
		//customer_IDText, customer_nameText, customer_PHNOText, tokenText, order_id1Text;
		removeAll();
		CustomerList = new List(6);
		loadCustomer();
		add(CustomerList);
		
		//When a list item is selected populate the text fields
		CustomerList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM customer");
					while (rs.next()) 
					{
						if (rs.getString("customer_id").equals(CustomerList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customer_IDText .setText(rs.getString("customer_id"));
						customer_nameText .setText(rs.getString("customer_name"));
						customer_PHNOText .setText(rs.getString("customer_PHNO"));
						tokenText.setText(rs.getString("token"));
						order_id1Text.setText(rs.getString("order_id"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		/*//Handle Update Menu Button
		updateButton = new Button("Update Customer");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE Customer "
					+ "SET Customerprice=" + customer_PHNOText .getText()  
					+ " WHERE Customerid = '" + CustomerList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					CustomerList.removeAll();
					loadCustomer();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});*/
		
		customer_IDText  = new TextField(15);
		customer_IDText .setEditable(false);
		customer_nameText  = new TextField(15);
		customer_nameText .setEditable(false);
		customer_PHNOText  = new TextField(15);
		customer_PHNOText .setEditable(false);
		tokenText = new TextField(15);
		tokenText.setEditable(false);
		order_id1Text=new TextField(15);
		order_id1Text.setEditable(false);
		

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customer_IDText );
		first.add(new Label("Customer Name:"));
		first.add(customer_nameText );
		first.add(new Label("Customer PHNO:"));
		first.add(customer_PHNOText );
		first.add(new Label("Token:"));
		first.add(tokenText);
		first.add(new Label("Order ID:"));
		first.add(order_id1Text);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		//third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewOrdersGUI() 
	{	
		removeAll();
		ordersList = new List(6);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM order_list");
					while (rs.next()) 
					{
						if (rs.getString("order_id").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						order_idText.setText(rs.getString("order_id"));
						item_idText.setText(rs.getString("item_id"));
						ordered_quantityText .setText(rs.getString("ordered_quantity"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		/*//Handle Update Menu Button
		updateButton = new Button("Update Order");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET orderprice=" + ordered_quantityText .getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});*/
		
		order_idText = new TextField(15);
		order_idText.setEditable(false);
		item_idText = new TextField(15);
		item_idText.setEditable(false);
		ordered_quantityText  = new TextField(15);
		ordered_quantityText .setEditable(false);
		//ordertypeText = new TextField(15);
		//ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Order ID:"));
		first.add(order_idText);
		first.add(new Label("Item ID:"));
		first.add(item_idText);
		first.add(new Label("Ordered quantity:"));
		first.add(ordered_quantityText );
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		//third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void viewSalesGUI() 
	{	//item_ID1Text, quantity_soldText, total_amountText
		removeAll();
		salesList = new List(6);
		loadSales();
		add(salesList);
		
		//When a list item is selected populate the text fields
		salesList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM sales");
					while (rs.next()) 
					{
						if (rs.getString("item_id").equals(salesList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						item_ID1Text.setText(rs.getString("item_id"));
						quantity_soldText.setText(rs.getString("quantity_sold"));
						total_amountText .setText(rs.getString("total_amount"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		/*//Handle Update Menu Button
		updateButton = new Button("Update Order");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET orderprice=" + ordered_quantityText .getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});*/
		//item_ID1Text, quantity_soldText, total_amountText
		
		item_ID1Text = new TextField(15);
		item_ID1Text.setEditable(false);
		quantity_soldText = new TextField(15);
		quantity_soldText.setEditable(false);
		total_amountText= new TextField(15);
		total_amountText.setEditable(false);
		//ordertypeText = new TextField(15);
		//ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Item ID:"));
		first.add(item_ID1Text);
		first.add(new Label("Quantity Sold:"));
		first.add(quantity_soldText);
		first.add(new Label("Total Amount:"));
		first.add(total_amountText );
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		//third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void viewEmployeeGUI() 
	{	// employee_IDText,employee_nameText,employee_PHNOText;
		removeAll();
		employeeList = new List(6);
		loadEmployee();
		add(employeeList);
		
		//When a list item is selected populate the text fields
		employeeList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM employee_list");
					while (rs.next()) 
					{
						if (rs.getString("employee_id").equals(employeeList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						employee_IDText.setText(rs.getString("employee_id"));
						employee_nameText.setText(rs.getString("employee_name"));
						employee_PHNOText.setText(rs.getString("employee_PHNO"));
						//ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		/*//Handle Update Menu Button
		updateButton = new Button("Update Order");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET orderprice=" + ordered_quantityText .getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});*/
		//employee_IDText,employee_nameText,employee_PHNOText
		
		employee_IDText = new TextField(15);
		employee_IDText.setEditable(false);
		employee_nameText = new TextField(15);
		employee_nameText.setEditable(false);
		employee_PHNOText= new TextField(15);
		employee_PHNOText.setEditable(false);
		//ordertypeText = new TextField(15);
		//ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(3, 2));
		first.add(new Label("Employee ID:"));
		first.add(employee_IDText);
		first.add(new Label("Employee Name:"));
		first.add(employee_nameText);
		first.add(new Label("Employee PHNO:"));
		first.add(employee_PHNOText);
		//first.add(new Label("Order Type:"));
		//first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		//third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void displaySQLErrors(SQLException e) 
	{
		errorText.append("\nSQLException: " + e.getMessage() + "\n");
		errorText.append("SQLState:     " + e.getSQLState() + "\n");
		errorText.append("VendorError:  " + e.getErrorCode() + "\n");
	}
	public void paint(Graphics g)
    {
		Font f= new Font("Arial",Font.BOLD,22);
		g.setFont(f);
    	g.drawString("BAKERY MANAGEMENT SYSTEM",100,450);
    }

public static void main(String[] args) 
	{
		InsertTables it = new InsertTables();
		it.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			System.exit(0);
		  }
		});
		it.buildFrame();
	}
}