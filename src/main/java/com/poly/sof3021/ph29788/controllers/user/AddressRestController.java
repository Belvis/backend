package com.poly.sof3021.ph29788.controllers.user;

import com.poly.sof3021.ph29788.dto.request.user.AddressRequestDTO;
import com.poly.sof3021.ph29788.dto.response.user.AddressResponseDTO;
import com.poly.sof3021.ph29788.services.user.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressRestController {

    private final AddressService addressService;

    @Autowired
    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<Page<AddressResponseDTO>> getAllAddresses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Page<AddressResponseDTO> addresses = addressService.getAll(page, size, sortField, sortOrder);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findAddressById(@PathVariable("id") Long id) {
        AddressResponseDTO address = addressService.getById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> saveAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO responseDTO = addressService.save(addressRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO, @PathVariable("id") Long id) {
        AddressResponseDTO responseDTO = addressService.update(addressRequestDTO, id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") Long id) {
        addressService.delete(id);
        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }
}
