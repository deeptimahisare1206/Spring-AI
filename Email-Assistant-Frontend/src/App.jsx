import { useState } from "react";

import "./App.css";
import axios from "axios";

function App() {
  const [email, setEmail] = useState("");
  const [tone, setTone] = useState("");
  const [reply, setReply] = useState("");
  const [loading, setLoading] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async () => {
setLoading(true);
setError('');
try {
  // API insertion remaining 
  const response = await axios.post("http://localhost:8080/api/email/generate",{
    emailContent:email,
    tone:tone
  });
  console.log(email);
  setReply(typeof response.data=== "string" ? response.data : JSON.stringify(response.data))
} catch (error) {
  setError("Failed to Generate Email Reply!");
  console.error(error);
}
finally{
setLoading(false);
}


  };
  return (
    <>
      <p className="text-5xl text-center py-4 text-[#d4ad54]">Email-Assistant</p>

      <div className="max-w-full min-h-[90vh] flex flex-col gap-4 items-center text-[#e4dcd0] py-2">
        <div className="flex flex-col w-1/2">
          <label className="text-2xl">Email Content: </label>
          <textarea
            className="border-2 w-full min-h-45 p-3"
            placeholder="Content of the emial here......."
            onChange={(e) => setEmail(e.target.value)}
          ></textarea>
        </div>

        <div className="min-w-1/2 min-h-full flex gap-1 items-center">
          <label className="text-xl">Tone: </label>

          <select
            className="border-2 w-full h-14 px-4"
            onChange={(e) => setTone(e.target.value)}
          >
            <option value="">Select</option>
            <option value="casual">Casual</option>
            <option value="friendly">Friendly</option>
            <option value="professional">Professional</option>
            <option value="formal">Formal</option>
          </select>
        </div>

        <button
          onClick={handleSubmit}
          className="bg-[#d4ad54] p-3 text-[#213e49] font-extrabold text-xl disabled:bg-gray-500"
          disabled={!email || loading}
        >
          {" "}
          Generate Reply{" "}
        </button>

        {reply && (

          <div className="flex flex-col w-1/2">
          <label className="text-2xl">Response: </label>
          <textarea
            className="border-2 w-full min-h-45 p-3"
            value={reply || ''}
            inputProps={{readOnly:true}}
          ></textarea>

           <button
          onClick={() => navigator.clipboard.writeText(reply)}
          className="bg-[#d4ad54] p-3 text-[#213e49] font-extrabold text-xl disabled:bg-gray-500"
          
        >Copy to Clipboard</button>
        </div>
        )
        }
      </div>
    </>
  );
}

export default App;
