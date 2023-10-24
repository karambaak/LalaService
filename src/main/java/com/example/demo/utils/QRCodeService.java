package com.example.demo.utils;

public interface QRCodeService {
    byte[] generateQRCode(String qrContent, int width, int height);
}
