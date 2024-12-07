package cl.tamila.firebase.servicios;

import cl.tamila.firebase.configuracion.FirebaseInitializer;
import cl.tamila.firebase.dto.CotizacionesDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CotizacionesServiceBueno {
    @Autowired
    private FirebaseInitializer firebase;

    public List<Map<String, Object>> listar() {
        List<Map<String, Object>> datos = new ArrayList<Map<String, Object>>();

        ApiFuture<QuerySnapshot> querySnapshotApiFuture =
                this.firebase.getFirestore().collection("cotizaciones").get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments())
            {
                //System.out.println("id="+doc.getId()+" | nombre="+doc.getString("nombre")+" | fecha="+doc.getString("fecha"));
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", doc.getId());
                map.put("nombre", doc.getString("nombre"));
                map.put("correo", doc.getString("correo"));
                map.put("telefono", doc.getString("telefono"));
                map.put("ciudad", doc.getString("ciudad"));
                map.put("direccion", doc.getString("direccion"));
                map.put("detalle", doc.getString("detalle"));
                map.put("fecha", doc.getString("fecha"));
                datos.add(map);
            }

            return datos;
        } catch (Exception e) {
            return null;
        }
    }
    public Map<String, Object> listarPorId(String id) {
        Map<String, Object> datos = new HashMap<>();
        try {

            ApiFuture<DocumentSnapshot> doc = firebase.getFirestore().collection("cotizaciones").document(id).get();
            DocumentSnapshot document = doc.get();
            if (document.exists()) {
                //System.out.println("si existe "+doc);
                datos.put("id", document.getId());
                datos.put("nombre", document.getString("nombre"));
                datos.put("correo", document.getString("correo"));
                datos.put("telefono", document.getString("telefono"));
                datos.put("ciudad", document.getString("ciudad"));
                datos.put("direccion", document.getString("direccion"));
                datos.put("detalle", document.getString("detalle"));
                datos.put("fecha", document.getString("fecha"));
                return datos;
                //return document.getData(); // Devuelve los datos del documento
            } else {
                //System.out.println("no existe "+doc);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no disponible");

            }
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no disponible");
        }
    }
    public boolean existeCorreo(String correo) {
        try {

            ApiFuture<QuerySnapshot> doc = firebase.getFirestore().collection("cotizaciones").whereEqualTo("correo", correo).get();
            QuerySnapshot document = doc.get();
            return !document.isEmpty();
        }
        catch (Exception e) {

            return false;
        }
    }
    public boolean guardar(CotizacionesDto dto)
    {
        try {
            //ApiFuture<WriteResult> save = firebase.getFirestore().collection("cotizaciones").document().create(dto);
            Map<String, Object> map = new HashMap<>();
            map.put("nombre", dto.getNombre());
            map.put("correo", dto.getCorreo());
            map.put("telefono", dto.getTelefono());
            map.put("ciudad", dto.getCiudad());
            map.put("direccion", dto.getDireccion());
            map.put("detalle", dto.getDetalle());
            Date myDate = new Date();
            map.put("fecha", new SimpleDateFormat("yyyy-mm-dd").format(myDate));
            ApiFuture<WriteResult> save = firebase.getFirestore().collection("cotizaciones").document().create(map);

            if(null != save.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
    public boolean editar(CotizacionesDto dto, String id)
    {
        try {

            ApiFuture<DocumentSnapshot> doc = firebase.getFirestore().collection("cotizaciones").document(id).get();
            DocumentSnapshot document = doc.get();
            if (document.exists()) {
                Map<String, Object> map = new HashMap<>();
                map.put("nombre", dto.getNombre());
                map.put("correo", dto.getCorreo());
                map.put("telefono", dto.getTelefono());
                map.put("ciudad", dto.getCiudad());
                map.put("direccion", dto.getDireccion());
                map.put("detalle", dto.getDetalle());
                ApiFuture<WriteResult> save = firebase.getFirestore().collection("cotizaciones").document(id).set(map);
                if(null != save.get()){
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no disponible");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no disponible");
            }
    }
    public boolean eliminar(String id)
    {
        try {
            ApiFuture<DocumentSnapshot> doc = firebase.getFirestore().collection("cotizaciones").document(id).get();
            DocumentSnapshot document = doc.get();
            if (document.exists()) {
                ApiFuture<WriteResult> delete = firebase.getFirestore().collection("cotizaciones").document(id).delete();

                return Boolean.TRUE;
            }else
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no disponible");
            }

    } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso no disponible");
    }
    }
}
