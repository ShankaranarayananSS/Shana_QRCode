#Importing Packages
import qrcode
from qrcode.constants import ERROR_CORRECT_L
from PIL import Image

# Creating Model for QR Code
qr = qrcode.QRCode(
    version=1,
    error_correction=ERROR_CORRECT_L,
    box_size=10,
    border=4,
)

#Getting URL Value and Photo File Name
url = input("Enter the URL to generate QR code: ").strip()
name = input("Enter a file name to save the QR code (without .png): ").strip()

#Adding URL Value to the Model
qr.add_data(url)
qr.make(fit=True)

# Creating QR
img = qr.make_image(fill_color="black", back_color="white")
#img = qr.make_image(fill_color="#000000", back_color="#FFFFFF")					//	Hex Value
#img = qr.make_image(fill_color=(0, 0, 0), back_color=(255, 255, 255))				//	RGB Value

# Saving File
file_name = f"{name}.png"
img.save(file_name)
print(f"QR code saved as {file_name}")

# Displaying Image
img.show()
