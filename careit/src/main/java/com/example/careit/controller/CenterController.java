package com.example.careit.controller;

import com.example.careit.dto.CenterDto;
import com.example.careit.service.CenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class CenterController {
    private final CenterService centerService;

    //센터 프로필 등록
    @PostMapping("/centers")
    public ResponseEntity<CenterDto> createCenter(@RequestBody @Valid CenterDto dto) {
        return ResponseEntity.ok(centerService.createCenter(dto));
    }

    // 센터 프로필 조회
    @GetMapping("/centers/{id}")
    public ResponseEntity<CenterDto> getCenter(@PathVariable Long id) {
        return ResponseEntity.ok(centerService.getCenterById(id));
    }

    // 센터 프로필 수정
    @PutMapping("/centers/{id}")
    public ResponseEntity<CenterDto> updateCenter(@PathVariable Long id, @RequestBody @Valid CenterDto dto) {
        return ResponseEntity.ok(centerService.updateCenter(id, dto));
    }

    // 센터 프로필 삭제
    @DeleteMapping("/centers/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}
