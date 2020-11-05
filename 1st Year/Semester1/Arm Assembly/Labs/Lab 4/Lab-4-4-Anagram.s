;
; CSU11021 Introduction to Computing I 2019/2020
; Anagrams
;

	AREA	RESET, CODE, READONLY
	ENTRY

	LDR	R0, =tststr1	; first string
	LDR	R1, =tststr2	; second string
	MOV	R8, R0		; create addressOneCopy
	MOV	R9, R1		; create addressTwoCopy
	LDR	R6, =0		; stringLengthOne = 0;
	LDR	R7, =0		; stringLengthTwo = 0;
	LDR	R10, ='@'

	; Store 1 in R0 if the Strings are anagrams
	; Store 0 in R0 if the Strings are not anagrams
	
whileOne			
	LDRB	R2, [R8]
	CMP	R2, #0		; while (character != endOfStringOne) {
	BEQ	whileTwo
	ADD	R6, R6, #1	; stringLengthOne++
	ADD	R8, R8, #1	; addressOneCopy++
	B	whileOne	; }
	
	
whileTwo
	LDRB	R3, [R9]
	CMP	R3, #0		; while (character != endOfStringTwo) {
	BEQ	compareStringLengths
	ADD	R7, R7, #1	; stringLengthTwo++
	ADD	R9, R9, #1	; addressTwoCopy++
	B	whileTwo	; }
	
compareStringLengths
	CMP	R6, R7		; if (stringLengthOne == stringLengthTwo) {
	BNE	notAnagrams	; 	Strings may be anagrams 
whStrOneNotEqZero		; }
	LDRB	R2, [R0]
	CMP	R2, #0		; while (chStr1 != end of String one) {
	BEQ	areAnagrams
	LDRB	R3, [R1]
	CMP	R2, R3		; if (chStr1 == chStr2) {
	BNE	individualChNotEq		
	STRB	R10, [R1]	; "cross off" letter to avoid repetition.
	ADD	R0, R0, #1	; addressOne++
	ADD	R1, R1, #1	; addressTwo++
	B	whileOne
	
individualChNotEq
	ADD	R1, R1, #1	; addressTwo++
	LDRB	R3, [R1]	; load value of ch2 from addressTwo 
	CMP	R3, #0		; if (chStrTwo != 0) { i.e end of StringTwo
	BEQ	notAnagrams
	CMP	R2, R3
	BNE	individualChNotEq
	
individualChEq
	ADD	R0, R0, #1	; next character in StringOne	
	LDR	R1, =tststr2	; reset stringOne back to original address
	B	whStrOneNotEqZero	
	
areAnagrams
	MOV	R0, #1		; Tells us (NOT IN ADDRESS) if the Strings are anagrams
	B	endNow
	
notAnagrams
	MOV	R0, #0		; Tells us (NOT IN THE ADDRESS AT R0) if Strings are not anagrams

endNow

STOP	B	STOP

tststr1	DCB	"tapas",0
tststr2	DCB	"pasts",0

	END
