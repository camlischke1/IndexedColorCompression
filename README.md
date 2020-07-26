# IndexedColorCompression
In computing, indexed color is a technique to manage digital images' colors in a limited fashion, in order to save computer memory and file storage, while speeding up display refresh and file transfers. It is a form of vector quantization compression.

When an image is encoded in this way, color information is not directly carried by the image pixel data, but is stored in a separate piece of data called a color lookup table (CLUT) or palette: an array of color specifications. Every element in the array represents a color, indexed by its position within the array. Each image pixel does not contain the full specification of its color, but only its index into the palette

THIS CODE CORRECTLY INDEXES THE COLORS OF THE FILE AND CREATES AN COLOR TABLE FOR THE USER-SPECIFIED NUMBER OF COLORS IN THE PRODUCT

● Allow the user to specify how many colors are desired, between 1 and 256. Save the number in a variable called ​N ​ . 
● Prompt the user for the name of the input file, the width, and the height. 
● Open a raw RGB image file. Raw RGB image files are available in the Resources folder on Sakai.  
● Read in the image file.  ○ The names of the files tell the pixel dimensions of the images. The dimensions are given as ​w ​ x ​h ​ where ​w ​ is the width and ​h ​ is the height of the file in pixels. RGB files have 3 bytes per pixel. Thus, each time through a ​for ​ loop that repeats w ​ x ​h ​ times, you read 3 bytes and store these RGB values in a ​w ​ x ​h ​ 2D array of some type, where each element in the array is an RGB value. 
● Count the frequencies of all the colors in the image. This is where you'll use a hash table. You can write your own hash table class, use the separate chaining or linear probing code in the book, or use the HashSet or HashMap in the Java API. 
● Sort the colors in descending order of frequency. 
● Choose the ​N ​ most frequently-occurring colors in the image. Save these ​N ​ colors in a table of RGB values. 
● Make a 2D array of size ​w ​ x ​h ​ to store the encodings of the pixels in the image. Call the table ​indexes ​ .  Each pixel's color will be saved in this table as one byte -- the index to the color in the color table.  ○ Go through the image a second time, and for each pixel (saved as 3 RGB bytes) find the color in your color table that is closest to the color of the pixel you're currently considering. For the pixel is row ​i ​ and column ​j ​ , save the index​ ​ of the chosen color in position ​i, j ​ . 
● Print out the color table. 
