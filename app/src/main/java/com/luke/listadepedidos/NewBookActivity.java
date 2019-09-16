package com.luke.listadepedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewBookActivity extends AppCompatActivity {

    private EditText mAuthor_editText;
    private EditText mTitle_editTxt;
    private EditText mISBN_editTxT;
    private Spinner mBook_categories_spinner;
    private Button mAdd_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        mAuthor_editText = (EditText) findViewById(R.id.author_editTxt);
        mTitle_editTxt = (EditText) findViewById(R.id.title_editTxt);
        mISBN_editTxT = (EditText) findViewById(R.id.isbn_editTxt);
        mBook_categories_spinner = (Spinner) findViewById(R.id.book_category_spinner);

        mAdd_btn = (Button) findViewById(R.id.update_btn);
        mBack_btn = (Button) findViewById(R.id.back_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new  Book();
                book.setAuthor(mAuthor_editText.getText().toString());
                book.setTitle(mTitle_editTxt.getText().toString());
                book.setIsbn(mISBN_editTxT.getText().toString());
                book.setCategory_name(mBook_categories_spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewBookActivity.this, "O book foi inserido " +
                                "com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

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
}
