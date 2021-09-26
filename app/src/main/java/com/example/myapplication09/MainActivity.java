package com.example.myapplication09;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper  myDb;
    EditText editName,editAddress,editTelephone,editHoliday_type;
    Button btnAddData;
    Button btnDelete;
    Button btnviewUpdate;
    private Object Message;
    private Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName =(EditText) findViewById(R.id.editText_name);
        editAddress =(EditText) findViewById(R.id.editText_address) ;
        editTelephone=(EditText) findViewById(R.id.editText_Telephone) ;
        editHoliday_type=(EditText) findViewById(R.id.editText_holiday_type);
                btnAddData = (Button) findViewById(R.id.button_add);
                btnviewAll = (Button) findViewById(R.id.button_viewAll);
        btnviewAll = (Button) findViewById(R.id.button_update);
        btnviewAll = (Button) findViewById(R.id.button_delete);
                AddData();
                viewAll();
                UpdateData();
        DeleteData();
    }

    public  void  DeleteData(){
        btnDelete.setOnClickListener(
                new View.onClickListener(){
                    public void onClick(View v){
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(MainActivity.this,"Data delete",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not delete",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }




    public void UpdateData (){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isUpdate = myDb.updareDate(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editAddress.getText().toString(),
                                editTelephone.getText().toString(),
                                editHoliday_type.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not update",Toast.LENGTH_LONG).show();
                }
                }
        );
    }


    public void AddData(){
      btnAddData.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                     boolean isInserted =  myDb.insertData(editName.getText().toString(),
                              editAddress.getText().toString(),
                              editTelephone.getText().toString(),
                              editHoliday_type.getText().toString()

                      );
                      if (isInserted = true)
                          Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                      else
                          Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();

                  }
              }
      );

    }




public void viewAll(){
    btnviewAll.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View v){
                    Cursor res  = myDb.getAllData();
                    if (res.getCount() == 0 ){
                        showMessage("ERROR","Nothing found");
                        //show message
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id:"+ res.getString(0)+"\n");
                        buffer.append("name:"+ res.getString(0)+"\n");
                        buffer.append("address:"+ res.getString(0)+"\n");
                        buffer.append("telephone:"+ res.getString(0)+"\n");
                        buffer.append("holiday_type:"+ res.getString(0)+"\n");
                    }

                    //show all data
                    showMessage("Data",buffer.toString());
                }
            }
    );
}
public void showMessage(String title, String Massage){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.show();
}

 }

