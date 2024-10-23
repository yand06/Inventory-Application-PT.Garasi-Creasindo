# 📱 Inventory Application - Sistem manajemen bahan baku digital dan produksi PT. Garasi Creasindo

![Project Status](https://img.shields.io/badge/status-active-success.svg)

## 📖 Tentang Project
Aplikasi ini adalah sistem manajemen inventory yang memungkinkan tracking bahan baku digital dan produksi secara real-time. Project ini dibuat untuk memudahkan PT. Garasi Creasindo dalam mengelola stok bahan baku digital dan produksi mereka secara efisien. Target pengguna dari aplikasi ini adalah staff inventory, production manager, dan admin PT. Garasi Creasindo.

## ⭐ Fitur Utama
- ✨ Dashboard dengan overview total stok bahan baku digital dan produksi
- 🚀 Manajemen stok masuk dan keluar untuk bahan baku digital
- 💡 Manajemen stok masuk dan keluar untuk bahan baku produksi
- 📊 Laporan detail stok dengan filter mutasi
- 🖨️ Fitur cetak laporan
- 🌓 Mode tema gelap/terang
- 🔐 Sistem autentikasi pengguna

## 🛠️ Dibuat Dengan
- [React.js](https://reactjs.org/) - Frontend Framework
- [Node.js](https://nodejs.org/) - Backend
- [MySQL](https://www.mysql.com/) - Database
- [Tailwind CSS](https://tailwindcss.com/) - Styling

## 🏁 Cara Menggunakan

```bash
# Clone repository ini
git clone https://github.com/garasi-creasindo/inventory-system

# Masuk ke direktori project
cd inventory-system

# Install dependencies
npm install

# Jalankan aplikasi
npm start
```

## 📷 Screenshot

### Dashboard
![Dashboard](screenshots/dashboard.png)
*Tampilan Dashboard dengan overview stok*

### Form Input
![Form Input](screenshots/form-input.png)
*Form input stok bahan baku*

### Laporan
![Laporan](screenshots/laporan.png)
*Tampilan laporan stok bahan baku*

## Fitur Detail

### Dashboard
- Total Bahan Baku Digital: Menampilkan jumlah total item digital
- Total Bahan Baku Produksi: Menampilkan jumlah total item produksi
- Total Bahan Baku Masuk: Tracking item yang masuk
- Total Bahan Baku Keluar: Tracking item yang keluar

### Manajemen Stok
- Input stok masuk dengan detail:
  - Nomor
  - Tanggal
  - Jenis Kertas
  - Ukuran
  - Mutasi (Masuk/Keluar)
  - Jumlah
- Filter view berdasarkan:
  - Semua Mutasi
  - Mutasi Masuk
  - Mutasi Keluar

### Laporan
- Laporan detail dengan informasi:
  - Nomor
  - Tanggal
  - Jenis Kertas
  - Ukuran
  - Status Mutasi
  - Jumlah
- Fitur cetak laporan

## 👤 Dibuat Oleh
**PT. Garasi Creasindo**
- Website: [www.garasicreasindo.com](https://www.garasicreasindo.com)
- Email: contact@garasicreasindo.com

## 📝 Lisensi
Project ini menggunakan lisensi [MIT](LICENSE)
