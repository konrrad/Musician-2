package Music;

import java.util.*;

public class Transposer {
    private final Set<String> possibleChords;
    private final Map<Character, Integer> nameToNumber;
    private final char[] numberToName= new char[]{'F', 'C','G','D','A','E','B'};

    public Transposer() {
        nameToNumber=new HashMap<>();
        nameToNumber.put('F',0);
        nameToNumber.put('C',1);
        nameToNumber.put('G',2);
        nameToNumber.put('D',3);
        nameToNumber.put('A',4);
        nameToNumber.put('E',5);
        nameToNumber.put('B',6);

        possibleChords=new HashSet<>(Arrays.asList("Cb","C","C#",
                "Db","D","D#","Eb","E","E#","Fb","F","F#","Gb","G","G#","Ab","A","A#","Bb","B","B#"));

    }

    public String transpose(String chords,boolean up)
    {
        StringBuilder buffer=new StringBuilder();
        int i=0,j=0;
        while(i<chords.length())
        {
            if(chords.charAt(i)!=' ')
            {
                j=i;
                while (j<chords.length()&&chords.charAt(j)!=' ')
                {
                    j++;
                    if(j-i>2)
                    {
                        throw new RuntimeException("Bad transpose input1");
                    }
                }
                if(j-i==2&&possibleChords.contains(chords.substring(i,i+1).toUpperCase()))
                {
                    buffer.append(this.goTranspose(chords.substring(i,j),up));
                    buffer.append(" ");
                }
                else if(j-i<2&&possibleChords.contains(chords.substring(i,j).toUpperCase()))
                {
                    buffer.append(this.goTranspose(chords.substring(i,j),up));
                    buffer.append(" ");
                }
                else if(j-i>2) throw new RuntimeException("Bad transpose input2");

            }
            i++;
        }
        return buffer.toString();
    }

    private String goTranspose(String chord, boolean up)
    {
        int multiplier= up? 1:-1;
        char[] characters=new char[2];
        int position=multiplier*7;
        char c=chord.charAt(0);
        position+=this.nameToNumber.get(Character.toUpperCase(c));
        if(chord.length()==2)
        {
            if(chord.charAt(1)=='#')
                position+=7;
            else if(chord.charAt(1)=='b')
                position-=7;
        }
        else if(chord.length()>2) throw new RuntimeException("too long chords");
        while(position>=12)
            position-=12;
        while(position<0)
            position+=12;

        characters[0]=Character.isUpperCase(c)?this.numberToName[Math.abs(position)%7]:Character.toLowerCase(this.numberToName[Math.abs(position)%7]);
        if(position/7==0)
            characters[1]=' ';
        else
            characters[1]='#';

        return new String(characters);

    }
}
