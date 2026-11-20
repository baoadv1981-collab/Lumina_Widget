# Lumina Widgets — Hướng dẫn tạo APK & đưa lên Google Play

## Cách 1: Tạo file .APK tự động không cần cài Android Studio (qua GitHub Actions)
1. Giải nén file `.zip` này.
2. Tạo một Repository mới trên GitHub và tải toàn bộ các file đã giải nén lên (bao gồm thư mục ẩn `.github/workflows/build-apk.yml`).
3. Vào tab **Actions** trên GitHub → chọn **Build Android APK** (tự động chạy trong ~2 phút).
4. Tải xuống artifact **app-debug-apk** → giải nén lấy `app-debug.apk` chép sang điện thoại Android để cài đặt dùng ngay.

## Cách 2: Mở trong Android Studio để xuất .APK hoặc .AAB lên Google Play
1. Giải nén và mở bằng **Android Studio** > **Open**.
2. Để cài trực tiếp lên điện thoại: chọn **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
3. Để đưa lên Google Play: chọn **Build > Generate Signed Bundle / APK... > Android App Bundle (.aab)**, ký bằng Keystore (`.jks`) và tải lên Google Play Console.

## Thông số dự án
- **Application ID**: `vn.luminawidgets.app`
- **Main Activity**: `vn.luminawidgets.app.MainActivity` (nút ghim widget trực tiếp lên màn hình chính)
- **Widget Class**: `vn.luminawidgets.app.widget.LuminaGlanceWidget`
- **Lưới mặc định**: `4x2` (`minWidth=250dp`, `minHeight=110dp`)
- **SDK mục tiêu**: `targetSdk = 35` (Android 15)
