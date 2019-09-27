package com.nevicelabs.photodiario;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Fragment que exibe a imagem selecionada pelo usuário
 * e o permite escrever um texto. A partir disso, é
 * gerado um objeto Postagem que será exibido na fragment
 * GaleriaFragment
 */
public class EditorFragment extends Fragment {

    public EditorFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editor, container, false);
    }

    /**
     * Este método faz parte do ciclo de vida do fragment. Chamado após a
     * view ser criada.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.imagemSelecionadaId);
        String uriString = getArguments().getString("uri");
        Uri uri = Uri.parse(uriString);

        try {
            // Pega a URI da imagem selecionada pelo usuário
            ParcelFileDescriptor parcelFileDescriptor =
                    getContext().getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap imagem = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();

            // Exibe a imagem
            imageView.setImageBitmap(imagem);
        } catch(IOException e) {
            // Faz algo
        }
    }
}

