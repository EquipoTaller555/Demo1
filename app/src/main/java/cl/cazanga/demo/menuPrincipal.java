package cl.cazanga.demo;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menuPrincipal extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_PDF = 1;

    private Uri permisoCirculacionUri, revisionTecnicaUri, soapUri;

    // Enum para identificar las secciones
    private enum DocumentType {
        PERMISO_CIRCULACION,
        REVISION_TECNICA,
        SOAP
    }

    private DocumentType currentDocumentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // Configuración de botones para Permiso de Circulación
        Button verPC = findViewById(R.id.verPC);
        Button agregarPC = findViewById(R.id.agregarPC);
        Button eliminarPC = findViewById(R.id.eliminar);
        Button actualizarPC = findViewById(R.id.actualizarPC);

        agregarPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDocumentType = DocumentType.PERMISO_CIRCULACION;
                pickPdf();  // Reutilizamos el método
            }
        });

        verPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPdf(permisoCirculacionUri);
            }
        });

        eliminarPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permisoCirculacionUri = null;
                Log.d("PDF", "Permiso de Circulación eliminado");
            }
        });

        actualizarPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDocumentType = DocumentType.PERMISO_CIRCULACION;
                pickPdf();
            }
        });

        // Configuración de botones para Revisión Técnica
        Button verRT = findViewById(R.id.verRT);
        Button agregarRT = findViewById(R.id.agregarRT);
        Button eliminarRT = findViewById(R.id.eliminarRT);
        Button actualizarRT = findViewById(R.id.actualizarRT);

        agregarRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDocumentType = DocumentType.REVISION_TECNICA;
                pickPdf();
            }
        });

        verRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPdf(revisionTecnicaUri);
            }
        });

        eliminarRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisionTecnicaUri = null;
                Log.d("PDF", "Revisión Técnica eliminada");
            }
        });

        actualizarRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDocumentType = DocumentType.REVISION_TECNICA;
                pickPdf();
            }
        });

        // Configuración de botones para SOAP
        Button verSOAP = findViewById(R.id.verSOAP);
        Button agregarSOAP = findViewById(R.id.agregarSOAP);
        Button eliminarSOAP = findViewById(R.id.eliminarSOAP);
        Button actualizarSOAP = findViewById(R.id.actualizarSOAP);

        agregarSOAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDocumentType = DocumentType.SOAP;
                pickPdf();
            }
        });

        verSOAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPdf(soapUri);
            }
        });

        eliminarSOAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soapUri = null;
                Log.d("PDF", "SOAP eliminado");
            }
        });

        actualizarSOAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDocumentType = DocumentType.SOAP;
                pickPdf();
            }
        });
    }

    // Método para abrir el explorador de archivos y seleccionar un PDF
    private void pickPdf() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_PICK_PDF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            switch (currentDocumentType) {
                case PERMISO_CIRCULACION:
                    permisoCirculacionUri = uri;
                    Log.d("PDF", "Permiso de Circulación seleccionado: " + uri.getPath());
                    break;
                case REVISION_TECNICA:
                    revisionTecnicaUri = uri;
                    Log.d("PDF", "Revisión Técnica seleccionada: " + uri.getPath());
                    break;
                case SOAP:
                    soapUri = uri;
                    Log.d("PDF", "SOAP seleccionado: " + uri.getPath());
                    break;
            }
        }
    }

    // Método para visualizar el PDF
    private void viewPdf(Uri pdfUri) {
        if (pdfUri != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(pdfUri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else {
            Log.d("PDF", "No hay archivo para visualizar");
        }
    }
}