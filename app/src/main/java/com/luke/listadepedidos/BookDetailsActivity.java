package com.luke.listadepedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {


    private EditText mAuthor_editTxt;
    private EditText mTitle_editTxt;
    private EditText mISBN_editTxt;
    private Spinner mBook_categories_spinner;

    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String author;
    private String title;
    private String category;
    private String isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        key = getIntent().getStringExtra("key");
        title = getIntent().getStringExtra("title");
        author = getIntent().getStringExtra("author");
        category = getIntent().getStringExtra("category");
        isbn = getIntent().getStringExtra("isbn");

        mAuthor_editTxt = (EditText) findViewById(R.id.author_editTxt);
        mAuthor_editTxt.setText(author);
        mTitle_editTxt = (EditText) findViewById(R.id.title_editTxt);
        mTitle_editTxt.setText(title);
        mISBN_editTxt = (EditText) findViewById(R.id.isbn_editTxt);
        mISBN_editTxt.setText(isbn);
        mBook_categories_spinner = (Spinner) findViewById(R.id.book_category_spinner);
        mBook_categories_spinner.setSelection(getIndex_SpinnerItem(mBook_categories_spinner, category));

        mUpdate_btn = (Button) findViewById(R.id.update_btn);
        mDelete_btn = (Button) findViewById(R.id.delete_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setTitle(mTitle_editTxt.getText().toString());
                book.setTitle(mAuthor_editTxt.getText().toString());
                book.setTitle(mISBN_editTxt.getText().toString());
                book.setTitle(mBook_categories_spinner.getSelectedItem().toString());


         new FirebaseDatabaseHelper().updateBook(key, book, new FirebaseDatabaseHelper.DataStatus() {
             @Override
             public void DataIsLoaded(List<Book> books, List<String> keys) {

             }

             @Override
             public void DataIsInserted() {

             }

             @Override
             public void DataIsUpdated() {
                 Toast.makeText(BookDetailsActivity.this, "Pedido " +
                         "Atualizado", Toast.LENGTH_LONG).show();
             }

             @Override
             public void DataIsDeleted() {

             }
         });
            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                    Toast.makeText(BookDetailsActivity.this, "Pedido foi " +
                    " deletado com sucesso", Toast.LENGTH_LONG).show();
                    finish(); return;
                    }
                });
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); return;
            }
        });
    }

    private int getIndex_SpinnerItem(Spinner spinner, String item){
      int index = 0;
      for(int i = 0; i<spinner.getCount(); i++){
          if(spinner.getItemAtPosition(i).equals(item)){
              index = i;
              break;
          }
      }
      return index;
    }
}
