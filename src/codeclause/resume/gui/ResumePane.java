/**
 * 
 */
package codeclause.resume.gui;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;


/**
 * @author mavhinamulisa
 * @version Online Resume Builder
 */
public class ResumePane extends GridPane{
	
	String imagePath = "/Users/mavhinamulisa/Desktop/2ND YEAR/INFORMATICS 2A/2ND SEMESTER/group project/homehub const pics/Floor Laying";
    String imageName = "Mulisa.jpeg";
	
	//For Personal Information part
	private Label lblSubHeader;
	private Label lblName;
	private TextField txtName;
	private Label lblSurname;
	private TextField txtSurname;
	private Label lblAddress1;
	private TextField txtAddress1;
	private Label lblAddress2;
	private TextField txtAddress2;
	private Label lblPostalCode;
	private TextField txtPostalCode;
	private Label lblNationality;
	private ComboBox<String> txtNationality;
	private Label lblDOF;
    private ComboBox<Integer> cmbDay;
    private ComboBox<String> cmbMonth;
    private ComboBox<Integer> cmbYear;
	private Label lblContact;
	private TextField txtContact;
	private Label lblEmail;
	private TextField txtEmail;
	
	//For skills Part
	private Label lblSubHeaderSkills;
	private TextField txtSkill1;
	private TextField txtSkill2;
	private TextField txtSkill3;
	private TextField txtSkill4;
	
	//For Work Experience
	private Label lblSubHeaderWork;
	private Label lblCompanyName1;
	private TextField txtCompanyName1;
	private Label lblWork1;
	private TextField txtWork1;
	private Label lblCompanyName2;
	private TextField txtCompanyName2;
	private Label lblWork2;
	private TextField txtWork2;
	private Label lblCompanyName3;
	private TextField txtCompanyName3;
	private Label lblWork3;
	private TextField txtWork3;
	
	//For Qualifications
	private Label lblSubHeaderQualify;
	private Label lblSchool;
	private TextField txtSchool;
	private Label lblQualificationtYPE;
	private Label lblQualificationA;
	private TextField txtQualificationA;
	private Label lblQualificationB;
	private TextField txtQualificationB;
	private ComboBox<String> cmbQualificationType;
	
	//For User Image
	private Label lblImage;
    private ImageView imageView;
	
	
	// Constructor
	public ResumePane() {
		
		setGUI();
		
	}
	
	// To setup the GUI
	private void setGUI() {
	    setVgap(10);
	    setHgap(10);
	    setAlignment(Pos.BASELINE_LEFT);
	    //setGridLinesVisible(true);

	    lblSubHeader = new Label("Personal Information");
	    lblSubHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    GridPane.setColumnSpan(lblSubHeader, 2);
	    add(lblSubHeader, 0, 1);
	    
	    lblName = new Label("First Name: ");
	    lblName.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblName,0,2);
	    txtName = new TextField();
	    add(txtName,1,2); // 1= col   2 = row
	    
	    lblSurname = new Label("Surname: ");
	    lblSurname.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblSurname,0,3);
	    txtSurname = new TextField();
	    add(txtSurname,1,3);
	    
	    lblAddress1 = new Label("Address Line 1: ");
	    lblAddress1.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblAddress1,0,4);
	    txtAddress1 = new TextField();
	    add(txtAddress1,1,4);
	    
	    lblAddress2 = new Label("Address Line 2: ");
	    lblAddress2.setStyle(" -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblAddress2,0,5);
	    txtAddress2 = new TextField();
	    add(txtAddress2,1,5);
	    
	    lblPostalCode = new Label("Postal Code: ");
	    lblPostalCode.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblPostalCode,0,6);
	    txtPostalCode = new TextField();
	    add(txtPostalCode,1,6);
	    
	    lblNationality = new Label("Nationality: ");
	    lblNationality.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblNationality,0,7);
	    ObservableList<String> nationalityOptions = FXCollections.observableArrayList(
	            "United States", "United Kingdom", "Canada", "South Africa", "Australia",
	            "Germany", "India", "Japan", "France", "Brazil",
	            "Mexico", "China", "Russia", "Italy", "Spain",
	            "Netherlands", "Sweden", "South Korea", "Turkey", "Norway"
	    );
	    txtNationality = new ComboBox<>(nationalityOptions);
	    add(txtNationality, 1, 7);
	    
	    lblDOF = new Label("Date of Birth: ");
	    lblDOF.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblDOF, 0, 8);

	    ObservableList<Integer> days = FXCollections.observableArrayList();
	    for (int i = 1; i <= 31; i++) {
	        days.add(i);
	    }
	    cmbDay = new ComboBox<>(days);

	    ObservableList<String> months = FXCollections.observableArrayList(
	            "January", "February", "March", "April", "May", "June",
	            "July", "August", "September", "October", "November", "December"
	    );
	    cmbMonth = new ComboBox<>(months);

	    ObservableList<Integer> years = FXCollections.observableArrayList();
	    for (int i = 1900; i <= 2024; i++) {  
	        years.add(i);
	    }
	    cmbYear = new ComboBox<>(years);

	    add(new Label("Day:"), 1, 8);
	    add(new Label("Month:"), 2, 8);
	    add(new Label("Year:"), 3, 8);
	    add(cmbDay, 1, 9);
	    add(cmbMonth, 2, 9);
	    add(cmbYear, 3, 9);
	    
	    
	    lblContact = new Label("Contact No: ");
	    lblContact.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblContact,0,10);
	    txtContact = new TextField();
	    add(txtContact,1,10);
	    
	    lblEmail = new Label("Email: ");
	    lblEmail.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblEmail,0,11);
	    txtEmail = new TextField();
	    add(txtEmail,1,11);
	    
	    // for skills
	    lblSubHeaderSkills = new Label("SKILLS");
	    lblSubHeaderSkills.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    GridPane.setColumnSpan(lblSubHeaderSkills, 2);
	    add(lblSubHeaderSkills, 5, 1);
	    txtSkill1 = new TextField();
	    add(txtSkill1,5,2);
	    txtSkill2 = new TextField();
	    add(txtSkill2,5,3);
	    txtSkill3 = new TextField();
	    add(txtSkill3,6,2);
	    txtSkill4 = new TextField();
	    add(txtSkill4,6,3);
	    
	    // for work experience
	    lblSubHeaderWork = new Label("WORK EXPERIENCE");
	    lblSubHeaderWork.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    GridPane.setColumnSpan(lblSubHeaderWork, 2);
	    add(lblSubHeaderWork, 5, 4);
	    
	    lblCompanyName1 = new Label("Company Name: ");
	    lblCompanyName1.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblCompanyName1,5,5);
	    txtCompanyName1 = new TextField();
	    add(txtCompanyName1,6,5);
	    lblWork1 = new Label("Work Done: ");
	    lblWork1.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblWork1,5,6); //cols //row
	    txtWork1 = new TextField();
	    add(txtWork1,6,6);
	    
	    lblCompanyName2 = new Label("Company Name: ");
	    lblCompanyName2.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblCompanyName2,5,7);
	    txtCompanyName2 = new TextField();
	    add(txtCompanyName2,6,7);
	    lblWork2 = new Label("Work Done: ");
	    lblWork2.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblWork2,5,8); //cols //row
	    txtWork2 = new TextField();
	    add(txtWork2,6,8);
	    
	    lblCompanyName3 = new Label("Company Name: ");
	    lblCompanyName3.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblCompanyName3,5,9);
	    txtCompanyName3 = new TextField();
	    add(txtCompanyName3,6,9);
	    
	    lblWork3 = new Label("Work Done: ");
	    lblWork3.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblWork3,5,10); //cols //row
	    txtWork3 = new TextField();
	    add(txtWork3,6,10);
	    
	    
	 // for work experience
	    lblSubHeaderQualify = new Label("QUALIFICATIONS");
	    lblSubHeaderQualify.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    GridPane.setColumnSpan(lblSubHeaderQualify, 2);
	    add(lblSubHeaderQualify, 0, 12);
	    
	    lblSchool = new Label("College/University Name: ");
	    lblSchool.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblSchool,0,13); 
	    txtSchool = new TextField();
	    add(txtSchool,1,13);
	    
	    lblQualificationtYPE = new Label("Qualification Type: ");
	    lblQualificationtYPE.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblQualificationtYPE,0,14); 
	    ObservableList<String> qualificationTypes = FXCollections.observableArrayList(
	            "High School Diploma", "Associate Degree", "Bachelor's Degree",
	            "Master's Degree", "Doctorate", "Professional Degree"
	    );
	    cmbQualificationType = new ComboBox<>(qualificationTypes);
	    add(cmbQualificationType, 1, 14);;
	    
	    lblQualificationA = new Label("Qualification A: ");
	    lblQualificationA.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblQualificationA,0,15); 
	    txtQualificationA = new TextField();
	    GridPane.setColumnSpan(txtQualificationA, 3);
	    add(txtQualificationA,1,15);

	    lblQualificationB = new Label("Qualification B: ");
	    lblQualificationB.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblQualificationB,0,16); 
	    txtQualificationB= new TextField();
	    GridPane.setColumnSpan(txtQualificationB, 3);
	    add(txtQualificationB,1,16);
	    
	    lblImage = new Label("Image");
	    lblImage.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: black; -fx-padding: 0 0 0 10;");
	    add(lblImage, 7, 1);

	    // Create a StackPane to overlay the Rectangle and ImageView
	    StackPane stackPane = new StackPane();

	    // Create a Rectangle for the background
	    Rectangle backgroundRectangle = new Rectangle();
	    backgroundRectangle.setFill(Color.GREY);
	    backgroundRectangle.setWidth(150); // Set the width of the rectangle
	    backgroundRectangle.setHeight(150); // Set the height of the rectangle

	    // Create an ImageView to display the selected image
	    imageView = new ImageView();
	    imageView.setFitWidth(150); 
	    imageView.setFitHeight(150); 

	    // Add the Rectangle and ImageView to the StackPane
	    stackPane.getChildren().addAll(backgroundRectangle, imageView);

	    // Add the StackPane to the GridPane
	    add(stackPane, 7, 2, 1, 4);

	    // Add a button to trigger the file chooser
	    Button btnUploadImage = new Button("Upload Image");
	    btnUploadImage.setOnAction(e -> uploadImage());
	    add(btnUploadImage, 7, 6);
	    
	    // Add a button to save the CV
        Button btnSaveCV = new Button("Save CV");
        btnSaveCV.setOnAction(e -> saveCV());
        add(btnSaveCV, 7, 7);

        // Add a button to clear all text fields
        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> clearFields());
        add(btnClear, 7, 8);
	}
	
	// Method to handle image upload using FileChooser
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }
    
    // To save the cv 
    private void saveCV() {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                float yStart = PDRectangle.A4.getHeight() - 50;
                float margin = 50;
                float yPosition = yStart;
                float width = PDRectangle.A4.getWidth() - 2 * margin;
                float height = 12;
                float lineSpacing = 15; // Adjust this value for proper line spacing

                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);

                // Gather data from text fields and image
                String name = txtName.getText();
                String surname = txtSurname.getText();
                String address1 = txtAddress1.getText();
                String address2 = txtAddress2.getText();
                String postalCode = txtPostalCode.getText();
                String nationality = txtNationality.getValue(); // Assuming txtNationality is a ComboBox
                int day = cmbDay.getValue();
                String month = cmbMonth.getValue();
                int year = cmbYear.getValue();
                String contact = txtContact.getText();
                String email = txtEmail.getText();
                String skill1 = txtSkill1.getText();
                String skill2 = txtSkill2.getText();
                String skill3 = txtSkill3.getText();
                String skill4 = txtSkill4.getText();
                String companyName1 = txtCompanyName1.getText();
                String work1 = txtWork1.getText();
                String companyName2 = txtCompanyName2.getText();
                String work2 = txtWork2.getText();
                String companyName3 = txtCompanyName3.getText();
                String work3 = txtWork3.getText();
                String school = txtSchool.getText();
                String qualificationType = cmbQualificationType.getValue();
                String qualificationA = txtQualificationA.getText();
                String qualificationB = txtQualificationB.getText();

                // Personal Information
                addTextWithSpacing(contentStream, "********************** Personal Information ********************" ,lineSpacing);
                addTextWithSpacing(contentStream, "Name: " + name, lineSpacing);
                addTextWithSpacing(contentStream, "Surname: " + surname, lineSpacing);
                addTextWithSpacing(contentStream, "Address1: " + address1, lineSpacing);
                addTextWithSpacing(contentStream, "Address2: " + address2, lineSpacing);
                addTextWithSpacing(contentStream, "Postal Code: " + postalCode, lineSpacing);
                addTextWithSpacing(contentStream, "Nationality: " + nationality, lineSpacing);
                addTextWithSpacing(contentStream, "Date of Birth: " + day + " " + month + " " + year, lineSpacing);
                addTextWithSpacing(contentStream, "Contact: " + contact, lineSpacing);
                addTextWithSpacing(contentStream, "Email: " + email, lineSpacing);

                // Qualifications
                addTextWithSpacing(contentStream, "************************* Qualifications **************************" ,lineSpacing);
                addTextWithSpacing(contentStream, "School attended: " + school, lineSpacing);
                addTextWithSpacing(contentStream, "Qualification Type: " + qualificationType, lineSpacing);
                addTextWithSpacing(contentStream, "Qualification A: " + qualificationA, lineSpacing);
                addTextWithSpacing(contentStream, "Qualification B: " + qualificationB, lineSpacing);

                // Skills
                addTextWithSpacing(contentStream, "******************************** Skills ****************************" ,lineSpacing);                
                addTextWithSpacing(contentStream, "Skills:", lineSpacing);
                addTextWithSpacing(contentStream, "  - " + skill1, lineSpacing);
                addTextWithSpacing(contentStream, "  - " + skill2, lineSpacing);
                addTextWithSpacing(contentStream, "  - " + skill3, lineSpacing);
                addTextWithSpacing(contentStream, "  - " + skill4, lineSpacing);

                // Work Experience
                addTextWithSpacing(contentStream, "************************* Work Experience **************************" ,lineSpacing);
                addTextWithSpacing(contentStream, "Work Experience:", lineSpacing);
                addTextWithSpacing(contentStream, "  - Company Name: " + companyName1 + ", Work Done: " + work1, lineSpacing);
                addTextWithSpacing(contentStream, "  - Company Name: " + companyName2 + ", Work Done: " + work2, lineSpacing);
                addTextWithSpacing(contentStream, "  - Company Name: " + companyName3 + ", Work Done: " + work3, lineSpacing);

                contentStream.endText();

             // Image
                if (imageView.getImage() != null) {
                	
                	float pageWidth = PDRectangle.A4.getWidth();
                	float pageHeight = PDRectangle.A4.getHeight();

                	float imageWidth = 100;  
                	float imageHeight = 100; 

                	// Adjust the x-coordinate to position the image at the top-right corner
                	float xCoordinate = pageWidth - imageWidth;

                	// Set the y-coordinate to position the image at the top-right corner
                	float yCoordinate = pageHeight - imageHeight - 50;
                	
                    try {
                        // Use Paths to create a correct file path
                        Path imageFilePath = Paths.get(imagePath, imageName);
                        File imageFile = imageFilePath.toFile();

                        if (imageFile.exists() && imageFile.isFile()) {
                            PDImageXObject pdImage = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);
                            contentStream.drawImage(pdImage, xCoordinate, yCoordinate, imageWidth, imageHeight);
                            
                        } else {
                            System.err.println("Image file not found: " + imageFile.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                
                
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Save the PDF to a file
            String pdfFilePath = "/Users/mavhinamulisa/Desktop/2ND YEAR/online courses/cv.pdf";
            document.save(pdfFilePath);
            System.out.println("CV Saved to: " + pdfFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // To create the spacing 
    private void addTextWithSpacing(PDPageContentStream contentStream, String text, float lineSpacing) throws IOException {
        contentStream.showText(text);
        contentStream.newLineAtOffset(0, -lineSpacing);
    }

    // To clear all the TextField 
    private void clearFields() {
        txtName.clear();
        txtSurname.clear();
    	txtAddress1.clear();
    	txtAddress2.clear();
    	txtPostalCode.clear();
    	txtContact.clear();
    	txtEmail.clear();
    	txtSkill1.clear();
    	txtSkill2.clear();
    	txtSkill3.clear();
    	txtSkill4.clear();
    	txtCompanyName1.clear();
    	txtWork1.clear();
    	txtCompanyName2.clear();
    	txtWork2.clear();
    	txtCompanyName3.clear();
    	txtWork3.clear();
    	txtSchool.clear();
    	txtQualificationB.clear();;
        // Clear other text fields as needed
 
    	txtNationality.setValue(null);
    	cmbDay.setValue(null);
        cmbMonth.setValue(null);
        cmbYear.setValue(null);
        cmbQualificationType.setValue(null);

        // Clear the image
        imageView.setImage(null);
    }



}
