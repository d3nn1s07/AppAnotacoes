package minhasanotacoes.studio.dennis.com.minhasanotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {



    private EditText texto;
    private ImageView salvar;
    private final static String NOME_ARQUIVO ="arquivo.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (EditText) findViewById(R.id.textoId);
        salvar = (ImageView) findViewById(R.id.salvarId);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoDigitado = texto.getText().toString();
                gravarArquivo(textoDigitado);

            }
        });

        if (lerArquivo() != null)
        {
            texto.setText(lerArquivo());
        }
    }

    private void gravarArquivo(String texto)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(texto);
            outputStreamWriter.close();

        }catch (IOException ex)
        {
            Log.v("MainActivity",ex.toString());
        }
    }
    private String lerArquivo()
    {
        String resultado="";
        try
        {
            InputStream arquivo = openFileInput(NOME_ARQUIVO);

            if(arquivo != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String linhaArquivo = "";
               while ((linhaArquivo = bufferedReader.readLine()) != null)
               {
                    resultado += linhaArquivo;
               }
            }
            arquivo.close();
        }
        catch (IOException ex)
        {
            Log.v("MainActivity",ex.toString());
        }
        return resultado;
    }
}
