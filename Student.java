import javax.swing.*;   //to import swing elements
import java.awt.*;      //to import flowlayout
import java.util.ArrayList; //to import arraylist
import java.awt.event.*;        //to import actionlistener

//this program is made for making a feedback form and store it in a arraylist which is to be displayed in text area

class Student extends JFrame implements ActionListener  //main class where the form is created
{
    JLabel l1,l2,l3,l4,l5,l6,l7;                //declaration of labels

    JTextField code,name;                       //declaration of various input parameters for the form
    JRadioButton yes,no;
    JRadioButton yes1,no1;
    JList standard;
    JRadioButton suff,insuff;
    JList nature;
    JTextArea area;
    JButton add,display;


    String[] stan={"Good","Medium","Low"};             //Strings for the JList
    String[] nat={"Tough","Normal","Easy"};

    ArrayList<Form> form=new ArrayList<Form>();       //the Arraylist

    Student()                                       //default constructor
    {
        JFrame jf=new JFrame();                     //Jframe
        jf.setTitle("Student Form");
        jf.setVisible(true);
        jf.setSize(1200,1200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLayout(new FlowLayout());

        l1=new JLabel("Course code:");              //course code
        code=new JTextField(10);
        jf.add(l1);
        jf.add(code);

        l2=new JLabel("Name:");                     //name
        name=new JTextField(10);
        jf.add(l2);
        jf.add(name);

        l3=new JLabel("Whether all required data are given?:");  //Whether all required data are given?
        yes=new JRadioButton("Yes");
        no=new JRadioButton("No");
        yes.setSelected(true);
        ButtonGroup g=new ButtonGroup();
        jf.add(l3);
        g.add(yes);
        g.add(no);
        jf.add(yes);
        jf.add(no);

        l4=new JLabel("Is any Question from out of syllabus:"); //Is any Question from out of syllabus
        yes1=new JRadioButton("Yes");
        no1=new JRadioButton("No");
        yes1.setSelected(true);
        ButtonGroup g1=new ButtonGroup();
        jf.add(l4);
        g1.add(yes1);
        g1.add(no1);
        jf.add(yes1);
        jf.add(no1);

        l5=new JLabel("Standard of Questions in QP");       //Standard of Questions in QP
        standard=new JList(stan);
        jf.add(l5);
        jf.add(standard);

        l6=new JLabel("Is time alloted Sufficient:");       //Is time alloted Sufficient
        suff=new JRadioButton("Sufficient");
        insuff=new JRadioButton("Insufficient");
        suff.setSelected(true);
        ButtonGroup g2=new ButtonGroup();
        jf.add(l6);
        g2.add(suff);
        g2.add(insuff);
        jf.add(suff);
        jf.add(insuff);

        l7=new JLabel("Nature of QP:");                 //Nature of QP
        nature=new JList(nat);
        jf.add(l7);
        jf.add(nature);

        area=new JTextArea(20,30);                      //textarea
        area.setSize(300,500);
        jf.add(area);

        add=new JButton("Add");                         //button for adding the form in arraylist and to be display in text area
        display=new JButton("Display");                 //button to display the form in the textarea
        jf.add(add);
        jf.add(display);
        add.addActionListener(this);
        display.addActionListener(this);
    }
    void Add() throws MyException                   //function for adding the form in the arraylist and then display it in the text area
    {
        String c=code.getText();
        String n=name.getText();
        int k=standard.getSelectedIndex();
        String st=stan[k];
        int a=nature.getSelectedIndex();
        String natu=nat[a];
        String dat="";
        String que="";
        String tim="";
        if(yes.isSelected())
        {
            dat="Yes";
        }
        else if(no.isSelected())
        {
            dat="No";
        }

        if(yes1.isSelected())
        {
            que="yes";
        }
        else if(no1.isSelected())
        {
            que="No";
        }

        if(suff.isSelected())
        {
            tim="Sufficient";
        }
        else if(insuff.isSelected())
        {
            tim="Insufficient";
        }

        String exception=c;
        if(c.isEmpty())      //exception check
        {
            throw new MyException(exception);
        }
        else
        {
            area.setText(c+" "+n+" "+dat+" "+que+" "+st+" "+tim+" "+natu);
            Form f=new Form(c,n,dat,que,st,tim,natu);
            form.add(f);
        }
      
    }
    void Display()    //function to display all form of student in the textarea
    {
        area.setText("");
        for (Form f : form) 
        {
            String data=f.getdata();
            area.append(""+data+"\n");
            System.out.println(data);
        }
    }
    public void actionPerformed(ActionEvent e)    //button action performed
    {
        if(e.getSource()==add)
        {
            try
            {
                Add();
            }
            catch(MyException k)
            {
                area.setText("invalid input:"+k);
            }
          
        }
        if(e.getSource()==display)
        {
            Display();
        }
    }


class MyException extends Exception  //User defined Exception
{
    String b;
    MyException(String a)
    {
        b=a;
    }
    public String toString()
    {
        return "Coursecode Missing";
    }
}

class Form   //class for storing information of the feedback form
{
    String coursecode;
    String name;
    String datagiven;
    String question;
    String standard;
    String time;
    String nature;
    Form(String coursecode,String name,String datagiven,String question,String standard,String time,String nature) //parameterized constructor
    {
        this.coursecode=coursecode;
        this.name=name;
        this.datagiven=datagiven;
        this.question=question;
        this.standard=standard;
        this.time=time;
        this.nature=nature;

    }
    public String getdata() //to return the infromation which then can be displayed
    {
        return coursecode+"\t"+name+"\t"+datagiven+"\t"+question+"\t"+standard+"\t"+time+"\t"+nature;
    }
}



    public static void main(String[] args)    //main function
    {
        Student s=new Student();
        
    }

}