import { useState } from "react";

import "./App.css";

function App() {
  return (
    <>
      <p className="text-5xl text-center  text-[#d4ad54]">Email-Assistant</p>

      <div className="max-w-full min-h-[90vh] flex flex-col gap-4 items-center text-[#e4dcd0] py-2">
        <div className="flex flex-col w-1/2">
          <label className="text-2xl">Email Content: </label>
          <textarea
            className="border-2 w-full min-h-45 p-3"
            placeholder="Content of the emial here......."
          ></textarea>
        </div>

        <div className="min-w-1/2 min-h-full flex gap-1 items-center">
          <label className="text-xl">Tone: </label>

          <select className="border-2 w-full h-14 px-4">
            <option value="">Select</option>
            <option value="casual">Casual</option>
            <option value="friendly">Friendly</option>
            <option value="professional">Professional</option>
            <option value="formal">Formal</option>
          </select>
        </div>
       <div className="flex flex-col w-1/2">
          <label className="text-2xl">Response: </label>
          <div
            className="border-2 w-full min-h-45 p-3"
            placeholder="Content of the emial here......."
          ></div>
        </div>
      </div>
    </>
  );
}

export default App;
