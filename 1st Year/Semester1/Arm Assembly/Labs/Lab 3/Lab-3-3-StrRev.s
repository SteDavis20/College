;
; CSU11021 Introduction to Computing I 2019/2020
; String Reverse
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr	; address of existing string
	LDR	R1, =0x40000000	; address for new string
	
	; Pseudo Code Answer: (location means the address)
	
	;while (chStr1 != 0) {
	;	locationOfStr1++;
	;}
	;endOfStr1 = locationOfStr1--;

	;while (locationOfStr1 != firstValueOfStr1) {
	;	chStr1 = chStr2;
	;	locationOfStr1--;
	;	locationOfStr2++;
	;}
while1				; use to find the end of the first string
	LDRB	R2, [R0]	; load value in memory at address R0 into R2
	CMP	R2, #0x00	; while (chStr1 != 0) {
	BEQ	endOfStr1	; branch to while2 if condition is false 
	ADD	R0, R0, #1	; address of Str1 = address + 1
	B	while1		; repeat iteration
endOfStr1
	SUB	R0, R0, #1	; Locate last character in string
while2
	LDRB	R2, [R0]	; load value in memory at address R0 into R2	
	CMP	R2, #0x00	; while address for new string != 0
	BEQ	endwh2		; branch to endwh2 if condition is false
	STRB	R2, [R1]	; store ascii value in R2 into memory address of new string
	SUB	R0, R0, #1	; address of String1 = address--
	ADD	R1, R1, #1	; address of Str2 = address++

	B	while2		; repeat iteration	
endwh2

STOP	B	STOP

tststr	DCB	"This is a test!",0

	END
