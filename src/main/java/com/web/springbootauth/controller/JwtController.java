package com.web.springbootauth.controller;

import com.web.springbootauth.CustomAuthenticationManager;
import com.web.springbootauth.entity.UserEntity;
import com.web.springbootauth.helper.JwtUtil;
import com.web.springbootauth.helper.ObjectToJson;
import com.web.springbootauth.model.JwtRequest;
import com.web.springbootauth.model.JwtResponse;
import com.web.springbootauth.model.JwtSuccessResponse;
import com.web.springbootauth.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/authentication")
    public ResponseEntity<Object> getAuthenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);

        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),
                            jwtRequest.getPassword()));
        }catch (DisabledException disabledException){
            System.out.println("User is disabled");
        }
        catch (AuthenticationException usernameNotFoundException){
            System.out.println("User not found :"+usernameNotFoundException.getMessage());
            throw new Exception("Bad credentials");
        }

        UserDetails userDetails=this.customUserDetailService.loadUserByUsername(jwtRequest.getUserName());

        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("Token : "+token);
//        System.out.println("userDetails : "+userDetails.toString());
        JwtResponse jwtResponse=new JwtResponse(token,userDetails);
//         String s= ObjectToJson.getStringFromObject(userDetails);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/priceLookup")
    @PreAuthorize("hasAnyRole('Store Associate','Cashier', 'Manager', 'Admin')")
    public ResponseEntity<Object> priceLookup() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Price Lookup"));
    }

    @PostMapping("/inventoryLookup")
    @PreAuthorize("hasAnyRole('Store Associate','Cashier', 'Manager', 'Admin')")
    public ResponseEntity<Object> inventoryLookup() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Inventory Lookup"));
    }

    @PostMapping("/billing")
    @PreAuthorize("hasAnyRole('Cashier', 'Manager', 'Admin')")
    public ResponseEntity<Object> billing() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Billing"));
    }

    @PostMapping("/returnMerchandise")
    @PreAuthorize("hasAnyRole('Cashier', 'Manager', 'Admin')")
    public ResponseEntity<Object> returnMerchandise() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Return Merchandise"));
    }


    @PostMapping("/exchangeMerchandise")
    @PreAuthorize("hasAnyRole('Cashier', 'Manager', 'Admin')")
    public ResponseEntity<Object> exchangeMerchandise() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access  Exchange Merchandise"));
    }

    @PostMapping("/storeOpen")
    @PreAuthorize("hasAnyRole( 'Manager', 'Admin')")
    public ResponseEntity<Object> storeOpen() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Store open"));
    }

    @PostMapping("/storeClose")
    @PreAuthorize("hasAnyRole( 'Manager', 'Admin')")
    public ResponseEntity<Object> storeClose() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Store Close"));
    }

    @PostMapping("/priceOverride")
    @PreAuthorize("hasAnyRole( 'Manager', 'Admin')")
    public ResponseEntity<Object> priceOverride() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Price override"));
    }

    @PostMapping("/inventoryUpdate")
    @PreAuthorize("hasRole( 'Admin')")
    public ResponseEntity<Object> inventoryUpdate() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are authorized to access Inventory update"));
    }

    @GetMapping("/accessDenied")
    public ResponseEntity<Object> accessDenied() throws Exception {

        return ResponseEntity.ok(new JwtSuccessResponse("You are Unauthorized to access this service"));
    }



}
