package com.tienda.controller;

/**
 *
 * @author kevin
 */
import com.tienda.service.ReporteService;
import jakarta.mail.Quota;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    ReporteService reporteService;
    
    @GetMapping("/principal")
    public String principal(Model model){
        Calendar fecha=Calendar.getInstance();
        String fechaIni=""+(fecha.get(Calendar.YEAR)-1)+"-01-01";
        
        String strMes=(fecha.get(Calendar.MONTH)<10?"0":"");
                fecha.get(Calendar.MONTH);
        String strDia=(fecha.get(Calendar.DAY_OF_MONTH)<10?"0":"");
                fecha.get(Calendar.DAY_OF_MONTH);
        String fechaFin=""+fecha.get(Calendar.YEAR)+"-"+strMes+"-"+strDia;
        return "/reportes/principal";
    }
     @GetMapping("/usuarios")
     public ResponseEntity<Resource> reporteClientes(@RequestParam String tipo)
             throws IOException{
        var reporte = "usuarios";
        return reporteService.generaReporte(reporte, null, tipo);
     }
     @GetMapping("/ventas")
     public ResponseEntity<Resource> reporteVentas(@RequestParam String tipo)
             throws IOException{
        var reporte = "ventas";
        return reporteService.generaReporte(reporte, null, tipo);
     }
      @GetMapping("/ventasTotales")
      public ResponseEntity<Resource> reporteVentasTotales(
            @RequestParam Date fechaInicio,
            @RequestParam Date fechaFin,
            @RequestParam String tipo) throws IOException{
          
        Map<String, Object> parametros = new HashMap();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        var reporte="ventasTotales";
        return reporteService.generaReporte(reporte, parametros, tipo);
      }
      
      @GetMapping("/categoria")
     public ResponseEntity<Resource> reporteCategorias(@RequestParam String tipo)
             throws IOException{
        var reporte = "categoria";
        return reporteService.generaReporte(reporte, null, tipo);
     }
     
     @GetMapping("/ProductsByPrice")
      public ResponseEntity<Resource> reporteProductosPorPrecio(
            @RequestParam Float precioInicial,
            @RequestParam Float precioFinal,
            @RequestParam String tipo) throws IOException{
          
        Map<String, Object> parametros = new HashMap();
        parametros.put("precioInicial", precioInicial);
        parametros.put("precioFinal", precioFinal);
        var reporte="ProductsByPrice";
        return reporteService.generaReporte(reporte, parametros, tipo);
      }
}
