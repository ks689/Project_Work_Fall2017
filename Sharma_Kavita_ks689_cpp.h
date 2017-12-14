#include <iostream>

#include <fstream>

#include <string>

#include <sstream>

using namespace std;



int main () {

  string line;

  //string a, b, c;

  float factor;

  float x, y, z;

  int a, b, c;

  ifstream infile ("wavefront.obj"); //specifying the input file. The file should be in the same location as the workspace

  ofstream outfile ("output.obj"); // Specify the name o the outputfile. The output file can be named with .obj.txt extension to see in formattable text file

  if (infile.is_open() && outfile.is_open())

  {

	  cout<< "Enter the scaling factor: ";

	  cin>>factor;



    while ( getline (infile,line) )

    {

    	std::istringstream in(line);      //make a stream for the line itself



    	    std::string type;

    	    in >> type;                  //and read the first whitespace-separated token



    	    if(type == "v")       //and check its value

    	    {

    	        if(factor == 0)
				{
				in >> x >> y >> z; 
				cout<<x<< " "<<y<<" "<<z <<"\n";

    	        outfile << "v "<<x<< " "<<y<<" "<<z<<"\n";
				
				}
				else{

    	        in >> x >> y >> z;       //now read the whitespace-separated floats

    	        x = x * factor;

    	        y = y * factor;

    	        z = z * factor;

    	        
    	        outfile << "v "<<x<< " "<<y<<" "<<z<<"\n";
				}

    	    }



    	    if(type == "f")

    	    {

    	    		in >> a >> b >> c;

    	    		outfile << "f "<<a<< " "<<b<<" "<<c<<"\n";


    	    }



    }
	
	cout<<"SCALED!!. Check the output file";

    infile.close();

    outfile.close();

  }



  else cout << "File not found";



  return 0;

}

