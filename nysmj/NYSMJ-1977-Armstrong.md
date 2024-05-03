### New York State Mathematics Journal

<p style="text-align: center;"><b><em>IN THE BAG</em></b></p>

<p style="text-align: right;">November 24, 1977</p>

To the Problems Editor:

Enclosed is a solution to problem no. 58 . . . .

&nbsp;. . . We have taken the liberty of extending the problem to other counting bases in the hope that this would further simulate some interest on the part of your readers. While our results resolve the problem as stated, the consideration of other counting bases seems to lead to some interesting questions; e.g., is there a simple formula for obtaining an Armstrong number of more than one digit when the counting base is even? (We have given such a result when the counting base is odd.) . . .

<ul><ul><ul><ul><ul><ul><ul><ul><ul><ul>
<ul style="list-style-type: none;">
    <li>Sincerely,</li>
    <ul style="list-style-type: none;">
        <li>James V. Peters</li>
        <ul style="list-style-type: none;">
            <li>for Richard P. O'Sullivan</li>
            <li>and James V. Peters</li>
            <ul style="list-style-type: none;">
                <li>St. Bonaventure University</li>
            </ul>
        </ul>
    </ul>
</ul>
</ul></ul></ul></ul></ul></ul></ul></ul></ul></ul>

&nbsp;58. (Gary Wernsing, Ithaca) An Armstrong number is an *n*-digit number equal to the sum of the *n*th powers of its digits. Examples: \(371 = 3^3 + 7^3 + 1^3\) and \(8208 = 8^4 + 2^4 + 0^4 + 8^4\). Prove that there are a finite number of Armstrong numbers.

&nbsp;An _n_-digit Armstrong number must be \(\ge 10^{n-1}\) since its leading digit is non-zero. At the same time, the sum of the *n*th powers of the digits cannot exceed \(n9^n\) It is simple to verify, using logarithms, that \(10^{n-1} \gt n9^n\) for \(n \ge 61\). This implies that no Armstrong number can contain 61 or more digits. Consequently, there are only finitely many such numbers.

&nbsp;More generally, if numbers are represented in terms of a counting base \(b\), then \(b^{n-1} \gt n(b-1)^n\) for \(n\) sufficiently large. For example, in binary \((b = 2)\) no Armstrong number can contain \(n = 3\) or more digits. This limits the search to 1, 10, 11. At the other extreme, as \(b \to \infty\) so does \(n\). The search for Armstrong numbers with respect to successively larger counting bases is, at least, non-vacuous. Indeed, it is not difficult to show that if \(b\) is odd, then \(aa\) is a two-digit Armstrong number when \(a = (b+1)/2\). Moreover, \(aa\) cannot be an Armstrong number with respect to any base \(b' \gt b\). To show this, we simply note that

\[
\left( b+1 \over 2 \right)b'+\left( b+1 \over 2 \right) = 2\left( b+1 \over 2 \right)^2
\]

implies \(b=b'\). Consequently there are an infinite number of *distinct* Armstrong numbers when one considers all counting bases simultaneously.

<!-- References -->
[1]: https://www.uucsr.org/people/jim-peters/
[2]: mailto:jpeters@liu.edu
[3]: https://github.com/patrodyne
[4]: mailto:rick.osullivan@verizon.net
