package com.example.alimentared.Service;

import com.example.alimentared.Dto.Response.ReporteImpactoResponseDto;
import com.example.alimentared.Repository.LoteAlimentoRepository;
import com.example.alimentared.Repository.OrganizacionRepository;
import com.example.alimentared.Repository.SolicitudDonacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final LoteAlimentoRepository loteRepository;
    private final SolicitudDonacionRepository solicitudRepository;
    private final OrganizacionRepository organizacionRepository;

    public ReporteImpactoResponseDto generarReporte() {
        ReporteImpactoResponseDto reporte = new ReporteImpactoResponseDto();

        reporte.setTotalLotesPublicados(loteRepository.count());
        reporte.setTotalSolicitudesRealizadas(solicitudRepository.count());
        reporte.setTotalOrganizacionesRegistradas(organizacionRepository.count());

        return reporte;
    }
}