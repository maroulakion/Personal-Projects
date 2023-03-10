#maroun 23-12-2023

#extract all integers from a string and return them into a list 

def extract_the_num(text):
    digits = ['0','1','2','3','4','5','6','7','8','9'] #not included ",", "."
    all_num = []
    i = 0
    while (i<len(text)):
        a_num = []
        if text[i] in digits:
            #each digit will be placed in one position of a list 
            a_num.append(text[i])

            #search for digits next to each other
            if i+1 < len(text):
                while text[i+1] in digits:
                    a_num.append(text[i+1])
                    if (i+1)+1 >= len(text):
                        break
                    else:
                        i = i + 1

                #add all digits together to make one string
                if (len(a_num) > 0):
                    num = a_num[0]
                    ii = 1
                    while(ii < len(a_num)):
                        num = num + a_num[ii]
                        ii = ii + 1
              
                    #casting string -> int
                    num = int(num)

                    #add number detected on the list with others found on string
                    all_num.append(num)
        i=i+1
    return all_num
        

str1 = "Participant Number:    8475"
str2 = "Alice finished university 1 years earlier, with an average grade of 3/4... Mindblowing!"
str3 = "No numbers"


all_num1 = extract_the_num(str1)    
all_num2 = extract_the_num(str2) 
all_num3 = extract_the_num(str3)

str_list = [str1, str2, str3]
all_num_list = [all_num1, all_num2, all_num3]

for i in range(len(str_list)):
    print("For string: '" + str_list[i] + "'")
    print("-> numbers extracted: " + str(all_num_list[i]) + "\n")
