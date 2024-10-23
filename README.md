# 🖥️ Goods Inventory Application - PT. Garasi Creasindo

![Project Status](https://img.shields.io/badge/status-active-success.svg)

## 📖 About the Project
This desktop application is an inventory management system that allows real-time tracking of digital raw materials and production. This project was created to make it easier for PT. Garasi Creasindo to manage their digital raw material stock and production efficiently. The target users of this application are inventory staff, production manager, and admin of PT. Garasi Creasindo who work in a desktop environment.

## ⭐ Key Features
- ✨ Dashboard with overview of total stock of digital raw materials and production
- 🚀 Inbound and outbound stock management for digital raw materials
- 💡 Incoming and outgoing stock management for production raw materials
- 📊 Detailed stock report with mutation filters
- 🖨️ Report print feature
- 🌓 Dark/light theme mode
- 🔐 User authentication system

## 🛠️ Made With
- [Java](https://www.java.com/) - Programming Language
- [JavaFX](https://openjfx.io/) - GUI Framework
- [Scene Builder](https://gluonhq.com/products/scene-builder/) - Visual Layout Tool
- [MySQL](https://www.mysql.com/) - Database
- [JasperReports](https://community.jaspersoft.com/) - Reporting Library

## 🏁 How to use

```bash
# Clone repository ini
git clone https://github.com/garasi-creasindo/inventory-system

# Masuk ke direktori project
cd inventory-system

# Compile project
javac -cp "lib/*" src/*.java

# Jalankan aplikasi
java -jar InventorySystem.jar
```

## 📷 Screenshot

### Loading Screen

![Loading](https://github.com/user-attachments/assets/0a8ca949-a2ef-40f7-878d-5a8ca28abdbc)

### Login Screen

![Login](https://github.com/user-attachments/assets/4de6ac85-b2dc-41be-bb4a-5564d27b9217)
*Dark mode login page view*

### Dashboard

![Utama](https://github.com/user-attachments/assets/38a2a02e-0d78-47e0-9fa0-df498d5d49c5)
*Dashboard view with stock overview*

### Form Input

**1. Form Input Bahan Baku Digital - Masuk** 

![Form BBD Masuk](https://github.com/user-attachments/assets/5b79af08-1170-4698-92eb-d4a363695990)

![Form BBD Keluar](https://github.com/user-attachments/assets/8810bed0-0322-401f-9d04-64bb8b30fb90)

![Form BBP Masuk](https://github.com/user-attachments/assets/5f0386a4-e1cf-4f22-8804-06b92f3a4b3f)

![Form BBP Keluar](https://github.com/user-attachments/assets/fa5b14eb-f842-43b0-b33b-d911c79cca0f)

*Raw material stock input form*

### Laporan
![Laporan](screenshots/laporan.png)
*Raw material stock report view*

## Detailed Features

### Dashboard
- Digital Raw Material Total: Displays the total number of digital items
- Total Production Raw Materials: Displays the total number of production items
- Total Incoming Raw Materials: Tracking incoming items
- Total Outgoing Raw Materials: Tracking outgoing items

### Stock Management
- Incoming stock input with details:
- Number
- Date
- Paper Type
- Size
- Mutation (In/Out)
- Amount
- Filter view by:
- All Mutations
- Incoming Mutations
- Outgoing Mutations

### Report
- Detailed report with information:
- Number
- Date
- Paper Type
- Size
- Mutation Status
- Quantity
- Export reports to PDF using JasperReports
- Direct print to printer feature

## 👤 Made By
**PT. Garasi Creasindo**
- Website: [www.garasicreasindo.com](https://www.garasicreasindo.com)
- Email: contact@garasicreasindo.com

## 📝 License
This project uses [MIT](LICENSE) license
