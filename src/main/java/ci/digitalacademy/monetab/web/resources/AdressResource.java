package ci.digitalacademy.monetab.web.resources;

import ci.digitalacademy.monetab.services.AddressService;
import ci.digitalacademy.monetab.services.dto.AddressDTO;

import ci.digitalacademy.monetab.services.dto.AddressDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/adresses")
public class AdressResource {

    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO){
        log.debug("REST Request to save  {}", addressDTO);
        return new ResponseEntity<>(addressService.save(addressDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id){
        log.debug(" REST Request to get one  ");
        Optional<AddressDTO> addressDTO = addressService.findOne(id);
        if (addressDTO.isPresent()) {
            return new ResponseEntity<>(addressDTO.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<?> getAddressBySlug(@PathVariable String slug) {
        log.info("REST Request to get Address by slug: {}", slug);
        Optional<AddressDTO> address = addressService.findOneAddressBySlug(slug);

        if (address.isPresent()) {
            return new ResponseEntity<>(address.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Address by slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiResponse(responseCode = "201", description = "List of address")
    public List<AddressDTO> getAllAddress(){
        log.debug("REST Request to all student ");
        return addressService.findAll();
    }


    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to update address")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        log.debug("REST request to update student with id: {}", id);

        Optional<AddressDTO> existingAddress = addressService.findOne(id);
        if (!existingAddress.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        addressDTO.setId(id);
        AddressDTO updatedAddress = addressService.save(addressDTO);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressDTO> partialUpdateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        log.debug("REST request to partially update student with id: {}", id);

        Optional<AddressDTO> existingAddress = addressService.findOne(id);
        if (!existingAddress.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AddressDTO currentAddress = existingAddress.get();

        if (addressDTO.getCity() != null) {
            currentAddress.setCity(addressDTO.getCity());
        }

        if (addressDTO.getStreet() != null) {
            currentAddress.setStreet(addressDTO.getStreet());
        }

        if (addressDTO.getCountry() != null) {
            currentAddress.setCountry(addressDTO.getCountry());
        }
        AddressDTO updatedAddress = addressService.save(currentAddress);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Request to delete")
    public void deleteAddress(@PathVariable Long id){
        log.debug("REST Request to delete  ");
        addressService.delete(id);
    }
}
