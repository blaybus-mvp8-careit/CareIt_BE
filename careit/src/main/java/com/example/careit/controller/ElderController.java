package com.example.careit.controller;


import com.example.careit.dto.ElderDTO;
import com.example.careit.service.ElderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elders")
@RequiredArgsConstructor
public class ElderController {
    private final ElderService elderService;

    // 어르신 등록
    @PostMapping("/{adminId}")
    public ResponseEntity<ElderDTO> registerElder(@PathVariable Long adminId, @RequestBody ElderDTO elderDto) {
        ElderDTO savedElder = elderService.registerElder(adminId, elderDto);
        return ResponseEntity.ok(savedElder);
    }

    // 특정 어르신 정보 조회
    @GetMapping("/{elderId}")
    public ResponseEntity<ElderDTO> getElderById(@PathVariable Long elderId) {
        ElderDTO elder = elderService.getElderById(elderId);
        return ResponseEntity.ok(elder);
    }

    // 특정 관리자가 등록한 어르신 (전체) 목록 조회
    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<ElderDTO>> getEldersByAdmin(@PathVariable Long adminId) {
        List<ElderDTO> elders = elderService.getEldersByAdminId(adminId);
        return ResponseEntity.ok(elders);
    }

    // 어르신 정보 수정
    @PutMapping("/{adminId}/{elderId}")
    public ResponseEntity<ElderDTO> updateElder(@PathVariable Long adminId,@PathVariable Long elderId, @RequestBody ElderDTO elderDto) {
        ElderDTO updatedElder = elderService.updateElder(adminId, elderId, elderDto);
        return ResponseEntity.ok(updatedElder);
    }

    // 어르신 삭제
    @DeleteMapping("/{elderId}")
    public ResponseEntity<String> deleteElder(@PathVariable Long elderId) {
        elderService.deleteElder(elderId);
        return ResponseEntity.ok("어르신 정보가 삭제되었습니다. ");
    }
}
